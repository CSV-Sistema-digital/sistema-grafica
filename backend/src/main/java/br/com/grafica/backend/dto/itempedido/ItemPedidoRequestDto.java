package br.com.grafica.backend.dto.itempedido;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;

import java.util.UUID;

public record ItemPedidoRequestDto(

        @NotNull
        UUID produtoId,

        @NotNull
        @Min(1)
        @Max(999)
        Integer quantidade
) {
}
