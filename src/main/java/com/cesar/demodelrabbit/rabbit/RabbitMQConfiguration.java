package com.cesar.demodelrabbit.rabbit;

import org.springframework.amqp.core.*;
import org.springframework.amqp.rabbit.config.RetryInterceptorBuilder;
import org.springframework.amqp.rabbit.connection.CachingConnectionFactory;
import org.springframework.amqp.rabbit.config.SimpleRabbitListenerContainerFactory;
import org.springframework.amqp.rabbit.retry.RejectAndDontRequeueRecoverer;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RabbitMQConfiguration {


    private static final String MAIN_QUEUE = "app.queue.Patata";
    private static final String DEAD_LETTER_QUEUE = "app.queue.deadletter";
    private static final String MAIN_EXCHANGE = "app.exchange";
    private static final String DEAD_LETTER_EXCHANGE = "app.exchange.deadletter";
    private static final String MAIN_ROUTING_KEY = "app.routingkey";
    private static final String DEAD_LETTER_ROUTING_KEY = "app.routingkey.deadletter";

    @Bean
    public Queue mainQueue() {
        return QueueBuilder.durable(MAIN_QUEUE)
                .withArgument("x-dead-letter-exchange", DEAD_LETTER_EXCHANGE)
                .withArgument("x-dead-letter-routing-key", DEAD_LETTER_ROUTING_KEY)
                .build();
    }

    @Bean
    public Queue deadLetterQueue() {
        return QueueBuilder.durable(DEAD_LETTER_QUEUE)
                .build();
    }

    @Bean
    public DirectExchange mainExchange() {
        return new DirectExchange(MAIN_EXCHANGE);
    }

    @Bean
    public DirectExchange deadLetterExchange() {
        return new DirectExchange(DEAD_LETTER_EXCHANGE);
    }

    @Bean
    public Binding mainBinding() {
        return BindingBuilder.bind(mainQueue())
                .to(mainExchange())
                .with(MAIN_ROUTING_KEY);
    }

    @Bean
    public Binding deadLetterBinding() {
        return BindingBuilder.bind(deadLetterQueue())
                .to(deadLetterExchange())
                .with(DEAD_LETTER_ROUTING_KEY);
    }

    @Bean
    public Jackson2JsonMessageConverter jsonMessageConverter() {
        return new Jackson2JsonMessageConverter();
    }

    @Bean
    public SimpleRabbitListenerContainerFactory listenerContainerFactory(
            CachingConnectionFactory connectionFactory) {
        SimpleRabbitListenerContainerFactory factory = new SimpleRabbitListenerContainerFactory();
        factory.setConnectionFactory(connectionFactory);
        factory.setMessageConverter(jsonMessageConverter());
        factory.setAdviceChain(RetryInterceptorBuilder
                .stateless()
                .maxAttempts(5)
                //.backOffOptions(1000, 2, 10000)
                .recoverer(new RejectAndDontRequeueRecoverer())
                .build()
        );
        return factory;
    }

    /*
    Cabecera reintentos x-count, test como funciona
        -implementarlo manualmente
        -
    Delegar en rabbitmq el encolado de la DLQ
    Probar el Exchange de reintentos espaciados en el tiempo.

     */
}
