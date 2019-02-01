package br.com.projetobackend.commons;

import java.util.List;
import java.util.Map;

import org.springframework.jdbc.core.RowMapper;

/**
 * @author vinicius.casani
 * @version 1.0
 * @since 28/01/2019.
 */
public interface RespositoryTemplate {

    Long insert( String sql, Map<String, Object> parameters, String key );

    void update( String sql, Map<String, Object> parameters );

    <T> List<T> query( String sql, RowMapper<T> mapper );

}
