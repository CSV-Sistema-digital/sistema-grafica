
package br.com.grafica.backend.controller;

import br.com.grafica.backend.dto.pagamento.PagamentoResponseDto;
import br.com.grafica.backend.dto.pedido.PedidoPreviaRequestDto;
import br.com.grafica.backend.dto.pedido.PedidoPreviaResponseDto;
import br.com.grafica.backend.dto.pedido.PedidoRequestDto;
import br.com.grafica.backend.dto.pedido.PedidoResponseDto;
import org.springframework.http.HttpStatus;
import br.com.grafica.backend.service.PedidoService;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("/v1/pedidos")
@RequiredArgsConstructor
public class PedidoController {

    private final PedidoService pedidoService;

    @PostMapping("/previa")
    public ResponseEntity<PedidoPreviaResponseDto> calcularPrevia(
            @Valid @RequestBody PedidoPreviaRequestDto request,
            @AuthenticationPrincipal Jwt jwt) {

        UUID authUserId = UUID.fromString(jwt.getSubject());

        return ResponseEntity.ok(
                pedidoService.calcularPrevia(request, authUserId)
        );
    }

    @PostMapping
    public ResponseEntity<PedidoResponseDto> criarPedido(
            @Valid @RequestBody PedidoRequestDto request,
            @AuthenticationPrincipal Jwt jwt) {

        UUID authUserId = UUID.fromString(jwt.getSubject());

        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(pedidoService.criarPedido(
                        request, authUserId
                ));
    }

    @PostMapping("/{id}/confirmar-pagamento")
    public ResponseEntity<PagamentoResponseDto> confirmarPagamento(
            @PathVariable UUID id,
            @AuthenticationPrincipal Jwt jwt) {

        UUID authUserId = UUID.fromString(jwt.getSubject());

        return ResponseEntity.ok(
                pedidoService.confirmarPagamento(id, authUserId)
        );
    }


}
