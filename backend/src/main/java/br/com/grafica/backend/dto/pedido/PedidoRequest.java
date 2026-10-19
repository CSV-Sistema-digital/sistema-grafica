package br.com.grafica.backend.dto.pedido;

import br.com.grafica.backend.dto.cliente.ClienteRequestDto;
import br.com.grafica.backend.dto.itempedido.ItemPedidoRequestDto;

import java.util.List;

public record PedidoRequest(

        ClienteRequestDto cliente,
        List<ItemPedidoRequestDto> itens
) {
}
