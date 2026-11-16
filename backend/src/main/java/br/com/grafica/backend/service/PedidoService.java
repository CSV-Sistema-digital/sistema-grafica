
package br.com.grafica.backend.service;

import br.com.grafica.backend.dto.itempedido.ItemPedidoRequestDto;
import br.com.grafica.backend.dto.itempedido.ItemPedidoPreviaResponseDto;
import br.com.grafica.backend.dto.pedido.PedidoPreviaRequestDto;
import br.com.grafica.backend.dto.pedido.PedidoPreviaResponseDto;
import br.com.grafica.backend.exception.ProdutoNaoEncontradoException;
import br.com.grafica.backend.model.Produto;
import br.com.grafica.backend.repository.ClienteRepository;
import br.com.grafica.backend.repository.ProdutoRepository;

import br.com.grafica.backend.model.Cliente;
import br.com.grafica.backend.model.Pedido;
import br.com.grafica.backend.model.ItemPedido;
import br.com.grafica.backend.model.Pagamento;

import br.com.grafica.backend.enums.StatusPedido;
import br.com.grafica.backend.enums.StatusPagamento;

import br.com.grafica.backend.repository.PedidoRepository;
import br.com.grafica.backend.repository.ItemPedidoRepository;
import br.com.grafica.backend.repository.PagamentoRepository;

import br.com.grafica.backend.dto.pedido.PedidoRequestDto;
import br.com.grafica.backend.dto.pedido.PedidoResponseDto;
import br.com.grafica.backend.dto.itempedido.ItemPedidoResponseDto;

import java.time.LocalDate;
import java.time.LocalDateTime;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class PedidoService {

    private final ProdutoRepository produtoRepository;
    private final ClienteRepository clienteRepository;
    private final PedidoRepository pedidoRepository;
    private final ItemPedidoRepository itemPedidoRepository;
    private final PagamentoRepository pagamentoRepository;

    @Transactional(readOnly = true)
    public PedidoPreviaResponseDto calcularPrevia(
            PedidoPreviaRequestDto request,
            UUID authUserId) {

        if (!clienteRepository.existsByAuthUserId(authUserId)) {
            throw new ResponseStatusException(
                    HttpStatus.NOT_FOUND,
                    "Cliente nao encontrado"
            );
        }

        Map<UUID, Integer> quantidades = new LinkedHashMap<>();

        for (ItemPedidoRequestDto item : request.itens()) {

            int quantidadeTotal = quantidades.merge(
                    item.produtoId(),
                    item.quantidade(),
                    Integer::sum
            );

            if (quantidadeTotal > 999) {
                throw new ResponseStatusException(
                        HttpStatus.BAD_REQUEST,
                        "Quantidade maxima por produto: 999"
                );
            }
        }

        List<ItemPedidoPreviaResponseDto> itens = new ArrayList<>();

        BigDecimal valorTotal = BigDecimal.ZERO;

        for (Map.Entry<UUID, Integer> entry : quantidades.entrySet()) {

            Produto produto = produtoRepository
                    .findByIdAndAtivoTrue(entry.getKey())
                    .orElseThrow(() ->
                            new ProdutoNaoEncontradoException(
                                    "Produto nao encontrado ou inativo"
                            )
                    );

            int quantidade = entry.getValue();

            BigDecimal subtotal = produto.getPreco()
                    .multiply(BigDecimal.valueOf(quantidade));

            valorTotal = valorTotal.add(subtotal);

            itens.add(new ItemPedidoPreviaResponseDto(
                    produto.getId(),
                    produto.getNome(),
                    quantidade,
                    produto.getPreco(),
                    subtotal
            ));
        }

        return new PedidoPreviaResponseDto(itens, valorTotal);
    }

    @Transactional
    public PedidoResponseDto criarPedido(
            PedidoRequestDto request,
            UUID authUserId) {

        Cliente cliente = clienteRepository
                .findByAuthUserId(authUserId)
                .orElseThrow(() -> new ResponseStatusException(
                        HttpStatus.NOT_FOUND,
                        "Cliente nao encontrado"
                ));

        PedidoPreviaResponseDto previa = calcularPrevia(
                new PedidoPreviaRequestDto(request.itens()),
                authUserId
        );

        if (request.precosConfirmados().size()
                != previa.itens().size()) {

            throw new ResponseStatusException(
                    HttpStatus.CONFLICT,
                    "PRECO_ALTERADO"
            );
        }

        for (ItemPedidoPreviaResponseDto item : previa.itens()) {

            BigDecimal precoConfirmado = request
                    .precosConfirmados()
                    .get(item.produtoId());

            if (precoConfirmado == null
                    || item.precoUnitario()
                    .compareTo(precoConfirmado) != 0) {

                throw new ResponseStatusException(
                        HttpStatus.CONFLICT,
                        "PRECO_ALTERADO"
                );
            }
        }

        Pedido pedido = new Pedido();

        pedido.setCliente(cliente);
        pedido.setData(LocalDate.now());
        pedido.setValorTotal(previa.valorTotal());
        pedido.setStatus(StatusPedido.PENDENTE);

        Pedido pedidoSalvo = pedidoRepository.save(pedido);

        List<ItemPedidoResponseDto> itensResposta =
                new ArrayList<>();

        for (ItemPedidoPreviaResponseDto item : previa.itens()) {

            Produto produto = produtoRepository
                    .findByIdAndAtivoTrue(item.produtoId())
                    .orElseThrow(() ->
                            new ProdutoNaoEncontradoException(
                                    "Produto nao encontrado ou inativo"
                            ));

            ItemPedido itemPedido = new ItemPedido();

            itemPedido.setPedido(pedidoSalvo);
            itemPedido.setProduto(produto);
            itemPedido.setQuantidade(item.quantidade());
            itemPedido.setPrecoUnitario(item.precoUnitario());

            ItemPedido itemSalvo =
                    itemPedidoRepository.save(itemPedido);

            itensResposta.add(new ItemPedidoResponseDto(
                    itemSalvo.getId(),
                    produto.getId(),
                    produto.getNome(),
                    itemSalvo.getQuantidade(),
                    itemSalvo.getPrecoUnitario()
            ));
        }

        Pagamento pagamento = new Pagamento();

        pagamento.setPedido(pedidoSalvo);
        pagamento.setFormaPagamento(request.formaPagamento());
        pagamento.setValor(previa.valorTotal());
        pagamento.setStatus(StatusPagamento.PENDENTE);
        pagamento.setData(LocalDateTime.now());

        pagamentoRepository.save(pagamento);

        return new PedidoResponseDto(
                pedidoSalvo.getId(),
                pedidoSalvo.getData(),
                itensResposta,
                pedidoSalvo.getValorTotal(),
                pagamento.getFormaPagamento(),
                pedidoSalvo.getStatus(),
                pagamento.getStatus()
        );
    }

}
