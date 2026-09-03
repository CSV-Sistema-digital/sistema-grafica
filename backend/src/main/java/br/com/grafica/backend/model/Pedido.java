package br.com.grafica.backend.model;

import br.com.grafica.backend.enums.StatusPedido;
import jakarta.persistence.*;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.UUID;

@Entity
@Table(name = "pedido")
public class Pedido {

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @ManyToOne
    @JoinColumn(name = "id_cliente", nullable = false)
    private Cliente cliente;

    @Column(name = "data", nullable = false)
    private LocalDate data;

    @Column(name = "valor_total",precision = 18, scale = 2,nullable = false)
    private BigDecimal valorTotal;

    @Enumerated(EnumType.STRING)
    @Column(name = "status", nullable = false, length = 30)
    private StatusPedido status;
}
