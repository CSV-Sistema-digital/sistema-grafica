
package br.com.grafica.backend.dto.itempedido;

import java.math.BigDecimal;
import java.util.UUID;

public record ItemPedidoPreviaResponseDto(

        UUID produtoId,
        String nomeProduto,
        int quantidade,
        BigDecimal precoUnitario,
        BigDecimal subtotal

) {
}
