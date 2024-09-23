package com.infnet.pedido.producer;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.infnet.pedido.configRabbit.RabbitConfig;
import org.apache.logging.log4j.message.Message;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
public class RabbitMQProducer {

    private static final Logger LOGGER = LoggerFactory.getLogger(RabbitMQProducer.class);

    private final RabbitTemplate rabbitTemplate;
    private final ObjectMapper objectMapper;

    @Autowired
    public RabbitMQProducer(RabbitTemplate rabbitTemplate, ObjectMapper objectMapper) {
        this.rabbitTemplate = rabbitTemplate;
        this.objectMapper = objectMapper;
    }

    public void sendMessage(Object message) throws JsonProcessingException {
        String messageJson = objectMapper.writeValueAsString(message);
        LOGGER.info("Mensagem enviada [{}]", messageJson);
        rabbitTemplate.convertAndSend(RabbitConfig.PEDIDO_EXCHANGE_NAME, RabbitConfig.PEDIDO_ROUTING_KEY, messageJson);
    }

    public void sendMessageToLogisticaQueue(Object message) throws JsonProcessingException {
        String messageJson = objectMapper.writeValueAsString(message);
        LOGGER.info("Enviando mensagem para empresaQueue [{}]", messageJson);
        rabbitTemplate.convertAndSend(RabbitConfig.LOGISTICA_EXCHANGE_NAME, RabbitConfig.LOGISTICA_ROUTING_KEY, messageJson);
    }
}
