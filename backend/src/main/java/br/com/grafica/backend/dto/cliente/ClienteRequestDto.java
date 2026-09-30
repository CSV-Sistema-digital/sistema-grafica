package br.com.grafica.backend.dto.cliente;

public record ClienteRequestDto(

        String nome,
        String cpf,
        String email,
        String telefone
) {
}
