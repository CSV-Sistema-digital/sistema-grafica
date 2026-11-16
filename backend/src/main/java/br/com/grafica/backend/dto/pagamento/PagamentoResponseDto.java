
package br.com.grafica.backend.dto.pagamento;

import br.com.grafica.backend.enums.FormaPagamento;
import br.com.grafica.backend.enums.StatusPagamento;
import br.com.grafica.backend.enums.StatusPedido;

import java.math.BigDecimal;
import java.util.UUID;

public record PagamentoResponseDto(

        UUID pedidoId,
        UUID pagamentoId,
        BigDecimal valor,
        FormaPagamento formaPagamento,
        StatusPedido statusPedido,
        StatusPagamento statusPagamento

) {
}
