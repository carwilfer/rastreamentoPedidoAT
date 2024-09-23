package com.infnet.logistica.consumer;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.infnet.logistica.dto.PedidoDTO;
import com.infnet.logistica.service.LogisticaService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class LogisticaListener {

    private static final Logger log = LoggerFactory.getLogger(LogisticaListener.class);

    @Autowired
    private LogisticaService logisticaService;

    @Autowired
    private ObjectMapper objectMapper;

    @RabbitListener(queues = "pedido_Queue")
    public void receberPedido(String message) {
        try {
            PedidoDTO pedidoDTO = objectMapper.readValue(message, PedidoDTO.class);
            pedidoDTO.getId();
            pedidoDTO.getProduto();
            pedidoDTO.getQuantidade();
            pedidoDTO.getStatus();
            System.out.println(message);
            boolean success = logisticaService.processarPedido(pedidoDTO);
            if (success) {
                log.info("Pedido atualizado com sucesso");
            }
        } catch (Exception e){
            log.error("Erro ao processar mensagem de pedido: {}", message, e);
        }

    }
}
