
package br.com.grafica.backend.controller;

import br.com.grafica.backend.dto.pedido.PedidoPreviaRequestDto;
import br.com.grafica.backend.dto.pedido.PedidoPreviaResponseDto;
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
}
