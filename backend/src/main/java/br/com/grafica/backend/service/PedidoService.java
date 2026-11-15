
package br.com.grafica.backend.service;

import br.com.grafica.backend.dto.itempedido.ItemPedidoRequestDto;
import br.com.grafica.backend.dto.itempedido.ItemPedidoPreviaResponseDto;
import br.com.grafica.backend.dto.pedido.PedidoPreviaRequestDto;
import br.com.grafica.backend.dto.pedido.PedidoPreviaResponseDto;
import br.com.grafica.backend.exception.ProdutoNaoEncontradoException;
import br.com.grafica.backend.model.Produto;
import br.com.grafica.backend.repository.ClienteRepository;
import br.com.grafica.backend.repository.ProdutoRepository;

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
}
