package com.infnet.estoque.service;

import com.infnet.estoque.model.Estoque;
import com.infnet.estoque.repository.EstoqueRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EstoqueService {

    @Autowired
    private EstoqueRepository estoqueRepository;

    public boolean verificarEstoque(String produto, Integer quantidade) {
        return estoqueRepository.findByProduto(produto)
                .map(e -> e.getQuantidade() >= quantidade)
                .orElse(false);
    }

    public Estoque adicionarProduto(Estoque estoque) {
        return estoqueRepository.save(estoque);
    }

    public Estoque buscarProduto(Long id) {
        return estoqueRepository.findById(id).orElseThrow(() -> new RuntimeException("Produto não encontrado!"));
    }

    public List<Estoque> listarEstoque() {
        return estoqueRepository.findAll();
    }

    public Estoque atualizarProduto(Long id, Estoque estoque) {
        Estoque existente = buscarProduto(id);
        existente.setProduto(estoque.getProduto());
        existente.setQuantidade(estoque.getQuantidade());
        return estoqueRepository.save(existente);
    }

    public void deletarProduto(Long id) {
        estoqueRepository.deleteById(id);
    }
}
