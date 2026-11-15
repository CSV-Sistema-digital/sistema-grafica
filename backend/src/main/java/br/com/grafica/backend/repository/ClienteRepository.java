
package br.com.grafica.backend.repository;

import br.com.grafica.backend.model.Cliente;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;
import java.util.UUID;

public interface ClienteRepository
        extends JpaRepository<Cliente, UUID> {

    Optional<Cliente> findByAuthUserId(UUID authUserId);

    boolean existsByAuthUserId(UUID authUserId);

    boolean existsByCpf(String cpf);

}
