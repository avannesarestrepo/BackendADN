package com.ceiba.propietario.adaptador.dao;

import com.ceiba.infraestructura.jdbc.CustomNamedParameterJdbcTemplate;
import com.ceiba.infraestructura.jdbc.EjecucionBaseDeDatos;
import com.ceiba.infraestructura.jdbc.sqlstatement.SqlStatement;
import com.ceiba.propietario.modelo.dto.PropietarioDTO;
import com.ceiba.propietario.puerto.dao.DaoPropietario;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.stereotype.Repository;

@Repository
public class DaoPropietarioMysql implements DaoPropietario {

    private final CustomNamedParameterJdbcTemplate customNamedParameterJdbcTemplate;
    @SqlStatement(namespace = "propietario", value = "obtenerpropietarioporid")
    private static String sqlObtenerPropietarioPorId;

    @SqlStatement(namespace = "propietario", value = "obtenerpropietariopordocumento")
    private static String sqlObtenerPropietarioPorDocumento;

    @SqlStatement(namespace = "propietario", value = "existepropietarioporid")
    private static String sqlExistePropietarioPorId;

    @SqlStatement(namespace = "propietario", value = "existepropietariopordocumento")
    private static String sqlExistePropietarioPorDocumento;

    public DaoPropietarioMysql(CustomNamedParameterJdbcTemplate customNamedParameterJdbcTemplate) {
        this.customNamedParameterJdbcTemplate = customNamedParameterJdbcTemplate;
    }

    @Override
    public Boolean existePorDocumento(String documento) {
        MapSqlParameterSource parameterSource = new MapSqlParameterSource();
        parameterSource.addValue("documento", documento);
        return this.customNamedParameterJdbcTemplate.getNamedParameterJdbcTemplate().queryForObject(sqlExistePropietarioPorDocumento,
                parameterSource, Boolean.class);
    }

    @Override
    public Boolean existePorId(Long id) {
        MapSqlParameterSource parameterSource = new MapSqlParameterSource();
        parameterSource.addValue("id", id);
        return this.customNamedParameterJdbcTemplate.getNamedParameterJdbcTemplate().queryForObject(sqlExistePropietarioPorId,
                parameterSource, Boolean.class);
    }

    @Override
    public PropietarioDTO obtenerPorId(Long id) {
        MapSqlParameterSource parameterSource = new MapSqlParameterSource();
        parameterSource.addValue("id", id);
        return EjecucionBaseDeDatos.obtenerUnObjetoONull(() ->
                this.customNamedParameterJdbcTemplate.getNamedParameterJdbcTemplate().queryForObject(sqlObtenerPropietarioPorId,
                        parameterSource, new MapeoPropietario()));
    }

    @Override
    public PropietarioDTO obtenerPorDocumento(String documento) {
        MapSqlParameterSource parameterSource = new MapSqlParameterSource();
        parameterSource.addValue("documento", documento);
        return EjecucionBaseDeDatos.obtenerUnObjetoONull(() ->
                this.customNamedParameterJdbcTemplate.getNamedParameterJdbcTemplate().queryForObject(sqlObtenerPropietarioPorDocumento,
                        parameterSource, new MapeoPropietario()));
    }
}
