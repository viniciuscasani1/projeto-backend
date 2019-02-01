package br.com.projetobackend.service;

import java.util.List;

import javax.inject.Inject;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import br.com.projetobackend.model.Ordem;
import br.com.projetobackend.repository.OrdemRepository;
import br.com.projetobackend.sender.ProcessaOrdemMessageSender;

/**
 * @author vinicius.casani
 * @version 1.0
 * @since 29/01/2019.
 */
@Service
@Transactional
public class OrdemService {

    private final ProcessaOrdemMessageSender processaOrdemMessageSender;
    private final OrdemRepository ordemRepository;

    @Inject
    public OrdemService(ProcessaOrdemMessageSender processaOrdemMessageSender, OrdemRepository ordemRepository) {
        this.processaOrdemMessageSender = processaOrdemMessageSender;
        this.ordemRepository = ordemRepository;
    }

    public void saveOrdem(Ordem ordem) {
        ordem = ordemRepository.save(ordem);
        processaOrdemMessageSender.sendProcessaAlteracaoLote(ordem);
    }

    public void updateOrdem(Ordem ordem) {
        ordem.setStatus("PROCESSADA");
        ordemRepository.update(ordem);
    }

    public List<Ordem> findOrdens() {
        return ordemRepository.listar();
    }


}
