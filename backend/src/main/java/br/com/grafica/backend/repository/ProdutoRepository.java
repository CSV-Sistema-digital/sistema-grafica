package br.com.grafica.backend.repository;

import br.com.grafica.backend.model.Produto;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.UUID;

public interface ProdutoRepository extends JpaRepository<Produto, UUID> {

    List<Produto> findByNomeContainingIgnoreCase(String nome);
}
