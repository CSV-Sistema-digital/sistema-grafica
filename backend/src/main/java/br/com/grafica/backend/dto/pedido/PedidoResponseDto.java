
package br.com.grafica.backend.dto.pedido;

import br.com.grafica.backend.dto.itempedido.ItemPedidoResponseDto;
import br.com.grafica.backend.enums.FormaPagamento;
import br.com.grafica.backend.enums.StatusPagamento;
import br.com.grafica.backend.enums.StatusPedido;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

public record PedidoResponseDto(

        UUID id,
        LocalDate data,
        List<ItemPedidoResponseDto> itens,
        BigDecimal valorTotal,
        FormaPagamento formaPagamento,
        StatusPedido statusPedido,
        StatusPagamento statusPagamento

) {
}
