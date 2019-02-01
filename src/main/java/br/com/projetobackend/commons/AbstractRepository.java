package br.com.projetobackend.commons;

import javax.inject.Inject;

/**
 * @author vinicius.casani
 * @version 1.0
 * @since 28/01/2019.
 */
public abstract class AbstractRepository{

    @Inject
    private RespositoryTemplate respositoryTemplate;

    protected RespositoryTemplate getRespositoryTemplate() {
        return respositoryTemplate;
    }
}
