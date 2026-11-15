
package br.com.grafica.backend.dto.pedido;

import br.com.grafica.backend.dto.itempedido.ItemPedidoPreviaResponseDto;

import java.math.BigDecimal;
import java.util.List;

public record PedidoPreviaResponseDto(

        List<ItemPedidoPreviaResponseDto> itens,
        BigDecimal valorTotal

) {
}
