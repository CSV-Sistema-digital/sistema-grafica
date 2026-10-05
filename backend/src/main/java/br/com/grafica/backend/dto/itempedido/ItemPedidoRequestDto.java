package br.com.grafica.backend.dto.itempedido;

import java.util.UUID;

public record ItemPedidoRequestDto(

        UUID produtoId,
        Integer quantidade
) {
}
