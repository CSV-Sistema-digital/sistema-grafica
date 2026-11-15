
package br.com.grafica.backend.repository;

import br.com.grafica.backend.model.Produto;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface ProdutoRepository
        extends JpaRepository<Produto, UUID> {

    List<Produto> findAllByAtivoTrue();

    List<Produto> findByNomeContainingIgnoreCaseAndAtivoTrue(
            String nome);

    Optional<Produto> findByIdAndAtivoTrue(UUID id);
}
