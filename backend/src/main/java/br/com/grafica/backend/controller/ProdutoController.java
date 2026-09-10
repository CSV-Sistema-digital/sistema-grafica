package br.com.grafica.backend.controller;

import br.com.grafica.backend.model.Produto;
import br.com.grafica.backend.service.ProdutoService;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api")
public class ProdutoController {

    private final ProdutoService produtoService;

    public ProdutoController(ProdutoService produtoService){
        this.produtoService = produtoService;

    }

    @GetMapping("/produto")
    public List<Produto> listarProdutos(){
        return produtoService.listarProdutos();
    }

    @GetMapping("produto/{id}")
    public Produto buscarProdutoPorId(@PathVariable("id") UUID id) {
        return produtoService.buscarPorId(id);
    }

    @GetMapping("/produto/pesquisa")
    public List<Produto> pesquisarPorNome(@RequestParam String nome){
        return produtoService.pesquisarPorNome(nome);
    }
}
