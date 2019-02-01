package br.com.projetobackend.model;

import java.math.BigDecimal;

/**
 * @author vinicius.casani
 * @version 1.0
 * @since 29/01/2019.
 */

public class Ordem {

    private Long idOrdem;
    private Double valorOrdem;
    private String descricaoOrdem;
    private String status;

    public Long getIdOrdem() {
        return idOrdem;
    }

    public void setIdOrdem( Long idOrdem ) {
        this.idOrdem = idOrdem;
    }

    public Double getValorOrdem() {
        return valorOrdem;
    }

    public void setValorOrdem( Double valorOrdem ) {
        this.valorOrdem = valorOrdem;
    }

    public String getDescricaoOrdem() {
        return descricaoOrdem;
    }

    public void setDescricaoOrdem( String descricaoOrdem ) {
        this.descricaoOrdem = descricaoOrdem;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus( String status ) {
        this.status = status;
    }

    @Override
    public String toString() {
        return "Ordem{" +
                    "idOrdem=" + idOrdem +
                    ", valorOrdem=" + valorOrdem +
                    ", descricaoOrdem='" + descricaoOrdem + '\'' +
                    ", status='" + status + '\'' +
                    '}';
    }
}
