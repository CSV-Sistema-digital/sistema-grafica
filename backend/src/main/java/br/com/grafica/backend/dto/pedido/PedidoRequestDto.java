
package br.com.grafica.backend.dto.pedido;

import br.com.grafica.backend.dto.itempedido.ItemPedidoRequestDto;
import br.com.grafica.backend.enums.FormaPagamento;

import jakarta.validation.Valid;
import jakarta.validation.constraints.*;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;
import java.util.UUID;

public record PedidoRequestDto(

        @NotEmpty
        List<@NotNull @Valid ItemPedidoRequestDto> itens,

        @NotNull
        FormaPagamento formaPagamento,

        @NotEmpty
        Map<@NotNull UUID,
                @NotNull @PositiveOrZero BigDecimal> precosConfirmados

) {
}
