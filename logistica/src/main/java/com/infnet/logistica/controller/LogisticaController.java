package com.infnet.logistica.controller;

import com.infnet.logistica.dto.PedidoDTO;
import com.infnet.logistica.model.Logistica;
import com.infnet.logistica.service.LogisticaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/logistica")
public class LogisticaController {

    @Autowired
    private LogisticaService logisticaService;

    @PostMapping
    public Logistica criarLogistica(@RequestBody PedidoDTO pedidoDTO) {
        return logisticaService.criarLogistica(pedidoDTO);
    }

    @GetMapping("/{id}")
    public Logistica buscarLogistica(@PathVariable Long id) {
        return logisticaService.buscarLogistica(id);
    }

    @GetMapping
    public List<Logistica> listarLogistica() {
        return logisticaService.listarLogistica();
    }

    @PutMapping("/{id}")
    public Logistica atualizarLogistica(@PathVariable Long id, @RequestBody PedidoDTO pedidoDTO) {
        return logisticaService.atualizarLogistica(id, pedidoDTO);
    }

    @DeleteMapping("/{id}")
    public void deletarLogistica(@PathVariable Long id) {
        logisticaService.deletarLogistica(id);
    }
}
