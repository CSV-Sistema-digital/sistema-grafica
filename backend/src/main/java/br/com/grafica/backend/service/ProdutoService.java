package br.com.grafica.backend.service;

import br.com.grafica.backend.model.Produto;
import br.com.grafica.backend.repository.ProdutoRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class ProdutoService {

    private final ProdutoRepository produtoRepository;

    public ProdutoService(ProdutoRepository produtoRepository){
        this.produtoRepository = produtoRepository;

    }

    public List<Produto> listarProdutos() {
        return produtoRepository.findAll();
    }

    public List<Produto> pesquisarPorNome(String nome){
        return produtoRepository.findByNomeContainingIgnoreCase(nome);
    }

    public Produto buscarPorId(UUID id){
        return produtoRepository.findById(id)
                .orElseThrow(()-> new RuntimeException("Produto não encontrado"));
    }
}
