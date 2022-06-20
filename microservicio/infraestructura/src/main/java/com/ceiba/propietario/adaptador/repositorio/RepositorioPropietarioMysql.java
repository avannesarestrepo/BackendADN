package com.ceiba.propietario.adaptador.repositorio;

import com.ceiba.infraestructura.jdbc.CustomNamedParameterJdbcTemplate;
import com.ceiba.infraestructura.jdbc.sqlstatement.SqlStatement;
import com.ceiba.propietario.modelo.entidad.Propietario;
import com.ceiba.propietario.puerto.repositorio.RepositorioPropietario;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.stereotype.Repository;

@Repository
public class RepositorioPropietarioMysql implements RepositorioPropietario {

    private static final String ID = "id";
    private static final String TIPO_DOCUMENTO = "tipo_documento";
    private static final String DOCUMENTO = "documento";
    private static final String NOMBRE_COMPLETO = "nombre_completo";
    private static final String EMAIL = "email";
    private static final String TELEFONO = "telefono";

    private final CustomNamedParameterJdbcTemplate customNamedParameterJdbcTemplate;

    @SqlStatement(namespace = "propietario", value = "guardarpropietario")
    private static String sqlGuardarPropietario;

    @SqlStatement(namespace = "propietario", value = "actualizarpropietario")
    private static String sqlActualizarPropietario;

    @SqlStatement(namespace = "propietario", value = "eliminarpropietario")
    private static String sqlEliminarPropietario;

    @SqlStatement(namespace = "propietario", value = "existepropietarioporid")
    private static String sqlExistePropietarioPorId;

    @SqlStatement(namespace = "propietario", value = "existepropietariopordocumento")
    private static String sqlExistePropietarioPorDocumento;

    public RepositorioPropietarioMysql(CustomNamedParameterJdbcTemplate customNamedParameterJdbcTemplate) {
        this.customNamedParameterJdbcTemplate = customNamedParameterJdbcTemplate;
    }

    @Override
    public Long guardar(Propietario propietario) {
        MapSqlParameterSource parameterSource = new MapSqlParameterSource();
        parameterSource.addValue(ID, propietario.getId());
        parameterSource.addValue(TIPO_DOCUMENTO, String.valueOf(propietario.getTipoDocumento()));
        parameterSource.addValue(DOCUMENTO, propietario.getDocumento());
        parameterSource.addValue(NOMBRE_COMPLETO, propietario.getNombreCompleto());
        parameterSource.addValue(EMAIL, propietario.getEmail());
        parameterSource.addValue(TELEFONO, propietario.getTelefono());
        return this.customNamedParameterJdbcTemplate.crear(parameterSource, sqlGuardarPropietario);
    }

    @Override
    public void actualizar(Propietario propietario) {
        MapSqlParameterSource parameterSource = new MapSqlParameterSource();
        parameterSource.addValue(ID, propietario.getId());
        parameterSource.addValue(TIPO_DOCUMENTO, String.valueOf(propietario.getTipoDocumento()));
        parameterSource.addValue(DOCUMENTO, propietario.getDocumento());
        parameterSource.addValue(NOMBRE_COMPLETO, propietario.getNombreCompleto());
        parameterSource.addValue(EMAIL, propietario.getEmail());
        parameterSource.addValue(TELEFONO, propietario.getTelefono());
        this.customNamedParameterJdbcTemplate.actualizar(parameterSource, sqlActualizarPropietario);
    }

    @Override
    public void eliminar(Long id) {
        MapSqlParameterSource parameterSource = new MapSqlParameterSource();
        parameterSource.addValue(ID, id);
        this.customNamedParameterJdbcTemplate.getNamedParameterJdbcTemplate().update(sqlEliminarPropietario, parameterSource);
    }

    @Override
    public Boolean existeConDocumento(String documento) {
        MapSqlParameterSource parameterSource = new MapSqlParameterSource();
        parameterSource.addValue(DOCUMENTO, documento);
        return this.customNamedParameterJdbcTemplate.getNamedParameterJdbcTemplate().queryForObject(sqlExistePropietarioPorDocumento,
                parameterSource, Boolean.class);
    }

    @Override
    public Boolean existeConId(Long id) {
        MapSqlParameterSource parameterSource = new MapSqlParameterSource();
        parameterSource.addValue(ID, id);
        return this.customNamedParameterJdbcTemplate.getNamedParameterJdbcTemplate().queryForObject(sqlExistePropietarioPorId,
                parameterSource, Boolean.class);
    }
}
