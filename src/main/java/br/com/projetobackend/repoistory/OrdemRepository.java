package br.com.projetobackend.repoistory;

import java.sql.*;
import java.util.List;
import java.util.Map;

import javax.inject.Inject;

import br.com.projetobackend.model.Ordem;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementCreator;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.BeanPropertySqlParameterSource;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

/**
 * @author vinicius.casani
 * @version 1.0
 * @since 29/01/2019.
 */
@Repository
public class OrdemRepository {

    private final JdbcTemplate jdbcTemplate;

    @Inject
    public OrdemRepository(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public List<Ordem> listar() {

        return jdbcTemplate.query("select * from tb_ordem", (resultSet, i) -> {

            Ordem ordem = new Ordem();
            ordem.setIdOrdem(resultSet.getLong("id_ordem"));
            ordem.setStatus(resultSet.getString("status"));
            ordem.setValorOrdem(resultSet.getDouble("valor_ordem"));
            ordem.setDescricaoOrdem(resultSet.getString("descricao_ordem"));

            return ordem;
        });
    }

    public Ordem save(Ordem ordem) {
        KeyHolder holder = new GeneratedKeyHolder();
        jdbcTemplate.update(connection -> {
            PreparedStatement ps = connection.prepareStatement("INSERT INTO TB_ORDEM (VALOR_ORDEM, STATUS, DESCRICAO_ORDEM) VALUES ( ?, ?, ? )", new String[]{"id_ordem"});
            ps.setDouble(1, ordem.getValorOrdem());
            ps.setString(2, ordem.getStatus());
            ps.setString(3, ordem.getDescricaoOrdem());
            return ps;
        }, holder);

        ordem.setIdOrdem(holder.getKey().longValue());
        return ordem;
    }

    public void update(Ordem ordem) {
        jdbcTemplate.update("UPDATE TB_ORDEM SET STATUS = ? WHERE ID_ORDEM= ?", ordem.getStatus(), ordem.getIdOrdem());
    }

}
