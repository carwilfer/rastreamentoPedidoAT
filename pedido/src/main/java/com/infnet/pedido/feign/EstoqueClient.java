package com.infnet.pedido.feign;

import com.infnet.pedido.dto.EstoqueDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name = "estoque-service", url = "http://localhost:8092")
public interface EstoqueClient {

    @GetMapping("/api/estoque/verificar/{produto}/{quantidade}")
    boolean verificarEstoque(@PathVariable("produto") String produto, @PathVariable("quantidade") Integer quantidade);
}