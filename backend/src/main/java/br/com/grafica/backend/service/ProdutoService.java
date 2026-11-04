package br.com.grafica.backend.service;

import br.com.grafica.backend.dto.produto.ProdutoResponseDto;
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

    public List<ProdutoResponseDto> listarProdutos() {
        return produtoRepository.findAll()
                .stream()
                .map(this::converterParaDto)
                .toList();
    }

    public List<ProdutoResponseDto> pesquisarPorNome(String nome){
        return produtoRepository.findByNomeContainingIgnoreCase(nome)
                .stream()
                .map(this::converterParaDto)
                .toList();
    }

    public ProdutoResponseDto buscarPorId(UUID id){
        Produto produto = produtoRepository.findById(id)
                .orElseThrow(()->
                        new RuntimeException("Produto não encontrado"));

        return converterParaDto(produto);
    }

    private ProdutoResponseDto converterParaDto(Produto produto){
        return new ProdutoResponseDto(
                produto.getId(),
                produto.getNome(),
                produto.getDescricao(),
                produto.getPreco(),
                produto.getImagem(),
                produto.getCategoria()
        );
    }
}
