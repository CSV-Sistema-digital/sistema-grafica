package br.com.grafica.backend.dto.produto;

import java.math.BigDecimal;
import java.util.UUID;

public record ProdutoResponseDto(

        UUID id,
        String nome,
        String descricao,
        BigDecimal preco,
        String imagem,
        String categoria
){
}
