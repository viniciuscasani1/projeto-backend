package br.com.projetobackend.repository;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import br.com.projetobackend.model.Ordem;

/**
 * @author vinicius.casani
 * @version 1.0
 * @since 29/01/2019.
 */
@Repository
public class OrdemRepository extends AbstractRepository {

    public List<Ordem> listar() {

        return super.query( "select * from tb_ordem", ( resultSet, i ) -> {
            Ordem ordem = new Ordem();
            ordem.setIdOrdem( resultSet.getLong( "id_ordem" ) );
            ordem.setStatus( resultSet.getString( "status" ) );
            ordem.setValorOrdem( resultSet.getDouble( "valor_ordem" ) );
            ordem.setDescricaoOrdem( resultSet.getString( "descricao_ordem" ) );
            return ordem;
        } );
    }

    public Ordem save( Ordem ordem ) {
        Map<String, Object> parameters = new HashMap<>();
        parameters.put( "vl_ordem", ordem.getValorOrdem() );
        parameters.put( "status", ordem.getStatus() );
        parameters.put( "descr", ordem.getDescricaoOrdem() );

        Long id = super.insert(
                    "INSERT INTO TB_ORDEM (VALOR_ORDEM, STATUS, DESCRICAO_ORDEM) VALUES ( :vl_ordem, :status, :descr )", parameters, "id_ordem" );
        ordem.setIdOrdem( id );
        return ordem;
    }

    public void update( Ordem ordem ) {
        Map<String, Object> parameters = new HashMap<>();
        parameters.put( "id_ordem", ordem.getIdOrdem() );
        parameters.put( "status", ordem.getStatus() );

        super.update( "UPDATE TB_ORDEM SET STATUS = :status WHERE ID_ORDEM = :id_ordem", parameters );
    }

}
