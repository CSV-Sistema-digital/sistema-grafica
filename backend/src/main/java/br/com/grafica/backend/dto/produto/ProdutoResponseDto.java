package br.com.grafica.backend.dto.produto;

import java.math.BigDecimal;

public record ProdutoResponseDto(

        String nome,
        String descricao,
        BigDecimal preco
){
}
