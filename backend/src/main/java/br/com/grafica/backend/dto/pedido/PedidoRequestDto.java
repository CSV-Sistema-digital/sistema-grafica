package br.com.grafica.backend.dto.pedido;

import br.com.grafica.backend.enums.StatusPedido;
import br.com.grafica.backend.model.Cliente;

import java.math.BigDecimal;
import java.time.LocalDate;

public record PedidoRequestDto(

        Cliente id_cliente,
        LocalDate data,
        BigDecimal valorTotal,
        StatusPedido status
) {
}
