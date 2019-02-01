package br.com.projetobackend.commons;

import java.util.List;
import java.util.Map;

import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;

/**
 * @author vinicius.casani
 * @version 1.0
 * @since 01/02/2019.
 */
public abstract class AbstractRespositoryTemplate implements RespositoryTemplate {

    private JdbcTemplate jdbcTemplate;
    private NamedParameterJdbcTemplate namedParameterJdbcTemplate;

    protected AbstractRespositoryTemplate( JdbcTemplate jdbcTemplate, NamedParameterJdbcTemplate namedParameterJdbcTemplate ) {
        this.jdbcTemplate = jdbcTemplate;
        this.namedParameterJdbcTemplate = namedParameterJdbcTemplate;
    }

    public NamedParameterJdbcTemplate getNamedParameterJdbcTemplate() {
        return this.namedParameterJdbcTemplate;
    }

    public Long insert( String sql, Map<String, Object> parameters, String key ) {
        MapSqlParameterSource parameterSource = new MapSqlParameterSource( parameters );
        KeyHolder keyHolder = new GeneratedKeyHolder();
        getNamedParameterJdbcTemplate().update( sql, parameterSource, keyHolder, new String[]{ key } );
        return keyHolder.getKey() != null ? keyHolder.getKey().longValue() : null;
    }

    public <T> List<T> query( String sql, RowMapper<T> rowMapper ) throws DataAccessException {
        return jdbcTemplate.query( sql, rowMapper );
    }

    public void update(String sql, Map<String, Object> parameters) {
        MapSqlParameterSource parameterSource = new MapSqlParameterSource( parameters );
        this.getNamedParameterJdbcTemplate().update(sql, parameterSource);
    }

}
