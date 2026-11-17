package br.com.grafica.backend.repository;

import java.util.List;
import java.util.Optional;
import br.com.grafica.backend.model.Pedido;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface PedidoRepository extends JpaRepository<Pedido, UUID> {

    Optional<Pedido> findByIdAndClienteAuthUserId(
            UUID id, UUID authUserId);

    List<Pedido> findAllByClienteAuthUserIdOrderByDataDesc(
            UUID authUserId);
}
