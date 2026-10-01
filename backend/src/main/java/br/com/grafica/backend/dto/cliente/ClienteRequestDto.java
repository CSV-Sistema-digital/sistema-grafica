package br.com.grafica.backend.dto.cliente;

public record ClienteRequestDto(

        String nome,
        String email,
        String telefone
) {
}
