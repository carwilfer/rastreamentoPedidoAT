package com.infnet.logistica.service;

import com.infnet.logistica.dto.PedidoDTO;
import com.infnet.logistica.exception.ResourceNotFoundException;
import com.infnet.logistica.model.Logistica;
import com.infnet.logistica.repository.LogisticaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class LogisticaService {

    @Autowired
    private LogisticaRepository logisticaRepository;

    public Logistica criarLogistica(PedidoDTO pedidoDTO) {
        Logistica logistica = new Logistica();
        logistica.setPedidoId(pedidoDTO.getId());
        logistica.setStatusEntrega("EM TRANSITO");
        return logisticaRepository.save(logistica);
    }

    public Logistica atualizarLogistica(Long id, PedidoDTO pedidoDTO) {
        Logistica logistica = logisticaRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Logistica não encontrada"));
        logistica.setPedidoId(pedidoDTO.getId());
        logistica.setStatusEntrega("EM TRANSITO");
        return logisticaRepository.save(logistica);
    }

    public void deletarLogistica(Long id) {
        logisticaRepository.deleteById(id);
    }

    public Logistica buscarLogistica(Long id) {
        return logisticaRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Logistica não encontrada"));
    }

    public List<Logistica> listarLogistica() {
        return logisticaRepository.findAll();
    }

    public boolean processarPedido(PedidoDTO pedidoDTO) {
        criarLogistica(pedidoDTO);
        return true; // Retorne um valor apropriado conforme necessário
    }
}
