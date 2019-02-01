package br.com.projetobackend.config;

import com.fasterxml.jackson.databind.ObjectMapper;

import org.springframework.amqp.core.*;
import org.springframework.amqp.rabbit.annotation.RabbitListenerConfigurer;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.rabbit.listener.RabbitListenerEndpointRegistrar;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.messaging.converter.MappingJackson2MessageConverter;
import org.springframework.messaging.handler.annotation.support.DefaultMessageHandlerMethodFactory;
import org.springframework.messaging.handler.annotation.support.MessageHandlerMethodFactory;

import javax.inject.Inject;

@Configuration
public class ConfigurationRabbit implements RabbitListenerConfigurer {

    public static final String FILA_PROCESSA_ORDEM = "FILA_PROCESSA_ORDEM";
    public static final String FILA_PROCESSA_ORDEM_DELAY = "FILA_PROCESSA_ORDEM_DELAY";
    public static final String DELAY = "delay";

    @Inject
    ObjectMapper objectMapper;

    @Bean
    Queue filaProcessaOrdem() {
        return QueueBuilder.durable( FILA_PROCESSA_ORDEM ).build();
    }

    @Bean
    Queue filaProcessaOrdemDelay() {
        return QueueBuilder.durable( FILA_PROCESSA_ORDEM_DELAY ).withArgument( "x-message-ttl", 120000 ).withArgument( "x-dead-letter-exchange", DELAY ).build();
    }

    @Bean
    Exchange delay() {
        return ExchangeBuilder.topicExchange( DELAY ).build();
    }

    @Bean
    Binding binding( Queue filaProcessaOrdem, TopicExchange delay ) {
        return BindingBuilder.bind( filaProcessaOrdem ).to( delay ).with( FILA_PROCESSA_ORDEM_DELAY );
    }

    @Bean
    public RabbitTemplate rabbitTemplate( final ConnectionFactory connectionFactory ) {
        final RabbitTemplate rabbitTemplate = new RabbitTemplate( connectionFactory );
        rabbitTemplate.setMessageConverter( producerJackson2MessageConverter() );
        return rabbitTemplate;
    }

    @Bean
    public Jackson2JsonMessageConverter producerJackson2MessageConverter() {

        return new Jackson2JsonMessageConverter( objectMapper );

    }

    @Override
    public void configureRabbitListeners( RabbitListenerEndpointRegistrar registrar ) {
        registrar.setMessageHandlerMethodFactory( messageHandlerMethodFactory() );
    }

    @Bean
    MessageHandlerMethodFactory messageHandlerMethodFactory() {
        DefaultMessageHandlerMethodFactory messageHandlerMethodFactory = new DefaultMessageHandlerMethodFactory();
        messageHandlerMethodFactory.setMessageConverter( consumerJackson2MessageConverter() );
        return messageHandlerMethodFactory;
    }

    @Bean
    public MappingJackson2MessageConverter consumerJackson2MessageConverter() {
        return new MappingJackson2MessageConverter();
    }

}
