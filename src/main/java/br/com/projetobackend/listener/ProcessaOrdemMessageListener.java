package br.com.projetobackend.listener;

import br.com.projetobackend.service.OrdemService;

import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import br.com.projetobackend.config.ConfigurationRabbit;
import br.com.projetobackend.model.Ordem;

/**
 * @author vinicius.casani
 * @version 1.0
 * @since 16/01/2019.
 */
@Component
public class ProcessaOrdemMessageListener {

    private final OrdemService ordemService;

    @Autowired
    public ProcessaOrdemMessageListener( OrdemService ordemService ) {
        this.ordemService = ordemService;
    }

    @RabbitListener( queues = ConfigurationRabbit.FILA_PROCESSA_ORDEM )
    public void processOrder( Ordem ordem ) {
        ordemService.updateOrdem( ordem );
    }

}