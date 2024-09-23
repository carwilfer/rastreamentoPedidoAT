package com.infnet.pedido.configRabbit;

import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.core.TopicExchange;
import org.springframework.amqp.support.converter.MessageConverter;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.amqp.rabbit.annotation.EnableRabbit;

@Configuration
@EnableRabbit
public class RabbitConfig {

    public static final String PEDIDO_QUEUE_NAME = "pedido_Queue";
    public static final String PEDIDO_EXCHANGE_NAME = "pedidoExchange";
    public static final String PEDIDO_ROUTING_KEY = "pedido.#";

    public static final String LOGISTICA_QUEUE_NAME = "logistica_Queue";
    public static final String LOGISTICA_EXCHANGE_NAME = "logisticaExchange";
    public static final String LOGISTICA_ROUTING_KEY = "logistica.#";

    @Bean
    public Queue pedidoQueue() {
        return new Queue(PEDIDO_QUEUE_NAME, true);
    }

    //para a fila da logistica
    @Bean
    public Queue logisticaQueue() {
        return new Queue(LOGISTICA_QUEUE_NAME, true); // true para durable
    }


    @Bean
    public TopicExchange pedidoExchange() {
        return new TopicExchange(PEDIDO_EXCHANGE_NAME);
    }

    //para a fila da empresa
    @Bean
    public TopicExchange logisticaExchange() {
        return new TopicExchange(LOGISTICA_EXCHANGE_NAME);
    }

    @Bean
    public Binding bindingPedido(Queue pedidoQueue, TopicExchange pedidoExchange) {
        return BindingBuilder.bind(pedidoQueue).to(pedidoExchange).with(PEDIDO_ROUTING_KEY);
    }
    //para a fila da empresa
    @Bean
    public Binding bindingLogistica(Queue logisticaQueue, TopicExchange logisticaExchange) {
        return BindingBuilder.bind(logisticaQueue).to(logisticaExchange).with(LOGISTICA_ROUTING_KEY);
    }

    @Bean
    public MessageConverter jsonMessageConverter() {
        return new Jackson2JsonMessageConverter();
    }
}
