package br.com.grafica.backend.controller;

import br.com.grafica.backend.dto.produto.ProdutoResponseDto;
import br.com.grafica.backend.model.Produto;
import br.com.grafica.backend.service.ProdutoService;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/v1/produtos")
public class ProdutoController {

    private final ProdutoService produtoService;

    public ProdutoController(ProdutoService produtoService){
        this.produtoService = produtoService;

    }

    @GetMapping()
    public List<ProdutoResponseDto> listarProdutos(){
        return produtoService.listarProdutos();
    }

    @GetMapping("/{id}")
    public ProdutoResponseDto buscarProdutoPorId(@PathVariable("id") UUID id) {
        return produtoService.buscarPorId(id);
    }

    @GetMapping("/pesquisa")
    public List<ProdutoResponseDto> pesquisarPorNome(@RequestParam String nome){
        return produtoService.pesquisarPorNome(nome);
    }
}
