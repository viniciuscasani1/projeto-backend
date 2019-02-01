package br.com.projetobackend.sender;

import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.projetobackend.config.ConfigurationRabbit;
import br.com.projetobackend.model.Ordem;

/**
 * @author vinicius.casani
 * @version 1.0
 * @since 16/01/2019.
 */
@Service
public class ProcessaOrdemMessageSender {

    private final RabbitTemplate rabbitTemplate;

    @Autowired
    public ProcessaOrdemMessageSender( RabbitTemplate rabbitTemplate ) {
        this.rabbitTemplate = rabbitTemplate;
    }

    public void sendProcessaAlteracaoLote( Ordem ordem ) {
        this.rabbitTemplate.convertAndSend( ConfigurationRabbit.FILA_PROCESSA_ORDEM_DELAY, ordem );
    }
}
