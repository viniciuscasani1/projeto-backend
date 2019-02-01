package br.com.projetobackend.controller;

import java.util.List;

import javax.inject.Inject;

import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import br.com.projetobackend.model.Ordem;
import br.com.projetobackend.service.OrdemService;

/**
 * @author vinicius.casani
 * @version 1.0
 * @since 29/01/2019.
 */
@RestController
@RequestMapping("ordem")
public class OrdemController {

    private final OrdemService ordemService;

    @Inject
    public OrdemController(OrdemService ordemService) {
        this.ordemService = ordemService;
    }

    @RequestMapping(method = RequestMethod.GET)
    public List<Ordem> findOrdens() {
        return ordemService.findOrdens();
    }

    @RequestMapping(method = RequestMethod.POST)
    public void saveOrdem(@RequestBody Ordem ordem) {
        ordemService.saveOrdem(ordem);
    }
}
