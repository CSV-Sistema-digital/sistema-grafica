package br.com.grafica.backend.dto.pedido;

import br.com.grafica.backend.dto.itempedido.ItemPedidoRequestDto;
import br.com.grafica.backend.enums.FormaPagamento;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;

import java.util.List;

public record PedidoRequestDto(

     @NotEmpty
     List<@Valid ItemPedidoRequestDto> itens,

     @NotNull
     FormaPagamento formaPagamento,

     @NotBlank
     String confirmacaoPreco
) {
}
