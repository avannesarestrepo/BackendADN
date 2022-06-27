package com.ceiba.propietario.adaptador.repositorio;

import com.ceiba.infraestructura.jdbc.CustomNamedParameterJdbcTemplate;
import com.ceiba.infraestructura.jdbc.EjecucionBaseDeDatos;
import com.ceiba.infraestructura.jdbc.sqlstatement.SqlStatement;
import com.ceiba.propietario.modelo.entidad.Propietario;
import com.ceiba.propietario.puerto.repositorio.RepositorioPropietario;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.stereotype.Repository;

@Repository
public class RepositorioPropietarioMysql implements RepositorioPropietario {

    private final MapeoPropietario mapeoPropietario;

    private static final String ID = "id";
    private static final String TIPO_DOCUMENTO = "tipo_documento";
    private static final String DOCUMENTO = "documento";
    private static final String NOMBRE_COMPLETO = "nombre_completo";
    private static final String EMAIL = "email";
    private static final String TELEFONO = "telefono";

    private final CustomNamedParameterJdbcTemplate customNamedParameterJdbcTemplate;

    @SqlStatement(namespace = "propietario", value = "crearpropietario")
    private static String sqlCrearPropietario;

    @SqlStatement(namespace = "propietario", value = "actualizarpropietario")
    private static String sqlActualizarPropietario;

    @SqlStatement(namespace = "propietario", value = "eliminarpropietario")
    private static String sqlEliminarPropietario;

    @SqlStatement(namespace = "propietario", value = "existepropietario")
    private static String sqlExistePropietario;

    @SqlStatement(namespace = "propietario", value = "obtenerpropietarioporid")
    private static String sqlObtenerPropietario;

    public RepositorioPropietarioMysql(MapeoPropietario mapeoPropietario, CustomNamedParameterJdbcTemplate customNamedParameterJdbcTemplate) {
        this.mapeoPropietario = mapeoPropietario;
        this.customNamedParameterJdbcTemplate = customNamedParameterJdbcTemplate;
    }

    @Override
    public Long crear(Propietario propietario) {
        MapSqlParameterSource parameterSource = new MapSqlParameterSource();
        parameterSource.addValue(ID, propietario.getId());
        parameterSource.addValue(TIPO_DOCUMENTO, String.valueOf(propietario.getTipoDocumento()));
        parameterSource.addValue(DOCUMENTO, propietario.getDocumento());
        parameterSource.addValue(NOMBRE_COMPLETO, propietario.getNombreCompleto());
        parameterSource.addValue(EMAIL, propietario.getEmail());
        parameterSource.addValue(TELEFONO, propietario.getTelefono());
        return this.customNamedParameterJdbcTemplate.crear(parameterSource, sqlCrearPropietario);
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
    public void eliminar(String documento) {
        MapSqlParameterSource parameterSource = new MapSqlParameterSource();
        parameterSource.addValue(DOCUMENTO, documento);
        this.customNamedParameterJdbcTemplate.getNamedParameterJdbcTemplate()
                .update(sqlEliminarPropietario, parameterSource);
    }

    @Override
    public Boolean existePropietario(Propietario propietario) {
        MapSqlParameterSource parameterSource = new MapSqlParameterSource();
        parameterSource.addValue(ID, propietario.getId());
        return this.customNamedParameterJdbcTemplate.getNamedParameterJdbcTemplate()
                        .queryForObject(sqlExistePropietario, parameterSource, Boolean.class);
    }

    @Override
    public Propietario obtenerPropietario(Propietario propietario) {
        MapSqlParameterSource parameterSource = new MapSqlParameterSource();
        parameterSource.addValue("id", propietario.getId());
        return EjecucionBaseDeDatos.obtenerUnObjetoONull(() -> this.customNamedParameterJdbcTemplate.getNamedParameterJdbcTemplate().queryForObject(sqlObtenerPropietario,
                parameterSource, mapeoPropietario));
    }

    @Override
    public Propietario obtenerPorId(Long id) {
        MapSqlParameterSource parameterSource = new MapSqlParameterSource();
        parameterSource.addValue("id", id);
        return EjecucionBaseDeDatos.obtenerUnObjetoONull(() ->
                this.customNamedParameterJdbcTemplate.getNamedParameterJdbcTemplate().queryForObject(sqlObtenerPropietario,
                        parameterSource, new MapeoPropietario()));
    }
}
