package br.com.grafica.backend.dto.cliente;

import java.util.UUID;

public record ClienteResponseDto(

        UUID id,
        String nome,
        String emai,
        String cpf,
        String telefone

) {
}
