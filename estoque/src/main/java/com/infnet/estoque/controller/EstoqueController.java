package com.infnet.estoque.controller;

import com.infnet.estoque.model.Estoque;
import com.infnet.estoque.service.EstoqueService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/estoque")
public class EstoqueController {

    @Autowired
    private EstoqueService estoqueService;

    @GetMapping("/verificar/{produto}/{quantidade}")
    public boolean verificarEstoque(@PathVariable String produto, @PathVariable Integer quantidade) {
        return estoqueService.verificarEstoque(produto, quantidade);
    }

    @PostMapping("/criar")
    public Estoque adicionarProduto(@RequestBody Estoque estoque) {
        return estoqueService.adicionarProduto(estoque);
    }

    @GetMapping("/{id}")
    public Estoque buscarProduto(@PathVariable Long id) {
        return estoqueService.buscarProduto(id);
    }

    @GetMapping
    public List<Estoque> listarEstoque() {
        return estoqueService.listarEstoque();
    }

    @PutMapping("/{id}")
    public Estoque atualizarProduto(@PathVariable Long id, @RequestBody Estoque estoque) {
        return estoqueService.atualizarProduto(id, estoque);
    }

    @DeleteMapping("/{id}")
    public void deletarProduto(@PathVariable Long id) {
        estoqueService.deletarProduto(id);
    }
}
