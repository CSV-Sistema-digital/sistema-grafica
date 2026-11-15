
package br.com.grafica.backend.controller;

import br.com.grafica.backend.dto.cliente.ClienteRequestDto;
import br.com.grafica.backend.dto.cliente.ClienteResponseDto;
import br.com.grafica.backend.service.ClienteService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("/v1/clientes")
@RequiredArgsConstructor
public class ClienteController {

    private final ClienteService clienteService;

    @PutMapping("/me")
    public ResponseEntity<ClienteResponseDto> salvarPerfil(
            @Valid @RequestBody ClienteRequestDto request,
            @AuthenticationPrincipal Jwt jwt) {

        UUID authUserId = UUID.fromString(jwt.getSubject());
        String email = jwt.getClaimAsString("email");

        return ResponseEntity.ok(
                clienteService.salvarPerfil(
                        request, authUserId, email
                )
        );
    }

    @GetMapping("/me")
    public ResponseEntity<ClienteResponseDto> buscarPerfil(
            @AuthenticationPrincipal Jwt jwt) {

        UUID authUserId = UUID.fromString(jwt.getSubject());

        return ResponseEntity.ok(
                clienteService.buscarClienteAutenticado(authUserId)
        );
    }
}
