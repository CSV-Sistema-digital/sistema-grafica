package br.com.grafica.backend.model;

import jakarta.persistence.*;

import java.math.BigDecimal;
import java.util.UUID;

@Entity
@Table(name = "item_pedido")
public class ItemPedido {

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @ManyToOne
    @JoinColumn(name = "id_pedido", nullable = false)
    private Pedido pedido;

    @ManyToOne
    @JoinColumn(name = "id_produto",nullable = false)
    private Produto produto;

    @Column(name = "quantidade", nullable = false)
    private int quantidade;

    @Column(name = "preco_unitario",precision = 18, scale = 2, nullable = false)
    private BigDecimal precoUnitario;
}
