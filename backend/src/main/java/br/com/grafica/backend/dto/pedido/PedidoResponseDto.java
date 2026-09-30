package br.com.grafica.backend.dto.pedido;

import br.com.grafica.backend.dto.cliente.ClienteRequestDto;
import br.com.grafica.backend.enums.StatusPedido;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.UUID;

public record PedidoResponseDto(

        UUID id,
        ClienteRequestDto clienteRequestDto,
        LocalDate data,
        BigDecimal valorTotal,
        StatusPedido statusPedido

) {
}
