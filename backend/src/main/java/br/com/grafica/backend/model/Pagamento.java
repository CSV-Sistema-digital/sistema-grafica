package br.com.grafica.backend.model;

import br.com.grafica.backend.enums.FormaPagamento;
import br.com.grafica.backend.enums.StatusPagamento;
import jakarta.persistence.*;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.UUID;

@Entity
@Table(name = "pagamento")
public class Pagamento {

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @OneToOne
    @JoinColumn(name = "id_pedido",nullable = false, unique = true)
    private Pedido pedido;

    @Enumerated(EnumType.STRING)
    @Column(name = "forma_pagamento",length = 30,nullable = false)
    private FormaPagamento formaPagamento;

    @Column(name = "valor", precision = 18, scale = 2, nullable = false)
    private BigDecimal valor;

    @Enumerated(EnumType.STRING)
    @Column(name = "status", length = 30, nullable = false)
    private StatusPagamento status;

    @Column(name = "data", nullable = false)
    private LocalDateTime data;
}
