package com.ceiba.propietario.adaptador.dao;

import com.ceiba.infraestructura.jdbc.CustomNamedParameterJdbcTemplate;
import com.ceiba.infraestructura.jdbc.EjecucionBaseDeDatos;
import com.ceiba.infraestructura.jdbc.sqlstatement.SqlStatement;
import com.ceiba.propietario.modelo.dto.PropietarioDTO;
import com.ceiba.propietario.puerto.dao.DaoPropietario;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class DaoPropietarioMysql implements DaoPropietario {

    private final CustomNamedParameterJdbcTemplate customNamedParameterJdbcTemplate;

    @SqlStatement(namespace = "propietario", value = "obtenerpropietarioporid")
    private static String sqlObtenerPropietario;
    @SqlStatement(namespace = "propietario", value = "obtenerpropietariopordocumento")
    private static String sqlObtenerPropietarioPorDocumento;

    @SqlStatement(namespace = "propietario", value = "obtenerpropietarios")
    private static String sqlObtenerPropietarios;

    public DaoPropietarioMysql(CustomNamedParameterJdbcTemplate customNamedParameterJdbcTemplate) {
        this.customNamedParameterJdbcTemplate = customNamedParameterJdbcTemplate;
    }

    @Override
    public PropietarioDTO obtenerPorId(Long id) {
        MapSqlParameterSource parameterSource = new MapSqlParameterSource();
        parameterSource.addValue("id", id);
        return EjecucionBaseDeDatos.obtenerUnObjetoONull(() ->
                this.customNamedParameterJdbcTemplate.getNamedParameterJdbcTemplate().queryForObject(sqlObtenerPropietario,
                        parameterSource, new MapeoPropietarioDTO()));
    }


    @Override
    public PropietarioDTO obtenerPorDocumento(String documento) {
        MapSqlParameterSource parameterSource = new MapSqlParameterSource();
        parameterSource.addValue("documento", documento);
        return EjecucionBaseDeDatos.obtenerUnObjetoONull(() ->
                this.customNamedParameterJdbcTemplate.getNamedParameterJdbcTemplate().queryForObject(sqlObtenerPropietarioPorDocumento,
                        parameterSource, new MapeoPropietarioDTO()));
    }

    @Override
    public List<PropietarioDTO> obtenerPropietarios() {
            return this.customNamedParameterJdbcTemplate.getNamedParameterJdbcTemplate().query(sqlObtenerPropietarios, new MapeoPropietarioDTO());
    }
}
