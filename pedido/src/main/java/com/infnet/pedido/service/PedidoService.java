package com.infnet.pedido.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import com.infnet.pedido.feign.EstoqueClient;
import com.infnet.pedido.model.Pedido;
import com.infnet.pedido.producer.RabbitMQProducer;
import com.infnet.pedido.repository.PedidoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PedidoService {

    @Autowired
    private PedidoRepository pedidoRepository;

    @Autowired
    private EstoqueClient estoqueClient;

    @Autowired
    private RabbitMQProducer rabbitMQProducer;

    private static final Logger LOGGER = LoggerFactory.getLogger(PedidoService.class);

    public Pedido criarPedido(Pedido pedido) {
        boolean estoqueDisponivel = estoqueClient.verificarEstoque(pedido.getProduto(), pedido.getQuantidade());
        LOGGER.info("Verificando estoque para produto: {}, quantidade: {}, disponível: {}", pedido.getProduto(), pedido.getQuantidade(), estoqueDisponivel);

        if (estoqueDisponivel) {
            pedido.setStatus("PROCESSANDO");
            Pedido novoPedido = pedidoRepository.save(pedido);
            try {
                rabbitMQProducer.sendMessage(novoPedido);
            } catch (JsonProcessingException e) {
                throw new RuntimeException("Erro ao enviar mensagem para o RabbitMQ", e);
            }
            return novoPedido;
        } else {
            throw new RuntimeException("Estoque insuficiente para o produto: " + pedido.getProduto());
        }
    }

    public Pedido buscarPedido(Long id) {
        return pedidoRepository.findById(id).orElseThrow(() -> new RuntimeException("Pedido não encontrado!"));
    }

    public List<Pedido> listarPedidos() {
        return pedidoRepository.findAll();
    }

    public Pedido atualizarPedido(Long id, Pedido pedido) {
        Pedido existente = buscarPedido(id);
        existente.setProduto(pedido.getProduto());
        existente.setQuantidade(pedido.getQuantidade());
        existente.setStatus(pedido.getStatus());
        return pedidoRepository.save(existente);
    }

    public void deletarPedido(Long id) {
        pedidoRepository.deleteById(id);
    }
}
