package br.com.grafica.backend.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.util.UUID;

@Entity
@Getter
@Setter
@Table(name = "produto")
public class Produto {

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @Column(name = "nome",nullable = false, length = 150)
    private String nome;

    @Column(name = "descricao",nullable = false, length = 150)
    private String descricao;

    @Column(name = "preco", nullable = false, precision = 18, scale = 2)
    private BigDecimal preco;

}
