package br.com.grafica.backend.dto.pedido;

import br.com.grafica.backend.enums.FormaPagamento;
import br.com.grafica.backend.enums.StatusPagamento;
import br.com.grafica.backend.enums.StatusPedido;
import br.com.grafica.backend.model.ItemPedido;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

public record PedidoResponseDto(

        UUID id,
        LocalDateTime data,
        List<ItemPedido> itens,
        BigDecimal valorTotal,
        FormaPagamento formaPagamento,
        StatusPedido statusPedido,
        StatusPagamento statusPagamento

) {
}
