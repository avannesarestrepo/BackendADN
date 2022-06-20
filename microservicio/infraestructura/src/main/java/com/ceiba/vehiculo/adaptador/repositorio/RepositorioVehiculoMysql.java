package com.ceiba.vehiculo.adaptador.repositorio;

import com.ceiba.infraestructura.jdbc.CustomNamedParameterJdbcTemplate;
import com.ceiba.infraestructura.jdbc.sqlstatement.SqlStatement;
import com.ceiba.vehiculo.modelo.entidad.Vehiculo;
import com.ceiba.vehiculo.puerto.repositorio.RepositorioVehiculo;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.stereotype.Repository;

@Repository
public class RepositorioVehiculoMysql implements RepositorioVehiculo {

    private static final String ID_VEHICULO= "id_vehiculo";
    private static final String ID_PROPIETARIO = "id_propietario";
    private static final String PLACA = "placa";
    private static final String DESCRIPCION = "descripcion";
    private static final String TIPO_VEHICULO = "tipo_vehiculo";

    private final CustomNamedParameterJdbcTemplate customNamedParameterJdbcTemplate;

    @SqlStatement(namespace = "vehiculo", value = "guardarvehiculo")
    private static String sqlGuardarVehiculo;

    @SqlStatement(namespace = "vehiculo", value = "actualizarvehiculo")
    private static String sqlActualizarVehiculo;

    @SqlStatement(namespace = "vehiculo", value = "eliminarvehiculo")
    private static String sqlEliminarVehiculo;

    @SqlStatement(namespace = "vehiculo", value = "existevehiculoporid")
    private static String sqlExisteVehiculoPorId;

    @SqlStatement(namespace = "vehiculo", value = "existevehiculoporplaca")
    private static String sqlExisteVehiculoPorPlaca;

    public RepositorioVehiculoMysql(CustomNamedParameterJdbcTemplate customNamedParameterJdbcTemplate) {
        this.customNamedParameterJdbcTemplate = customNamedParameterJdbcTemplate;
    }

    @Override
    public Long guardar(Vehiculo vehiculo) {
        MapSqlParameterSource parameterSource = new MapSqlParameterSource();
        parameterSource.addValue(ID_VEHICULO, vehiculo.getIdVehiculo());
        parameterSource.addValue(ID_PROPIETARIO, vehiculo.getIdPropietario());
        parameterSource.addValue(PLACA, vehiculo.getPlaca());
        parameterSource.addValue(DESCRIPCION, vehiculo.getDescripcion());
        parameterSource.addValue(TIPO_VEHICULO, String.valueOf(vehiculo.getTipoVehiculo()));
        return this.customNamedParameterJdbcTemplate.crear(parameterSource, sqlGuardarVehiculo, ID_VEHICULO);
    }

    @Override
    public void actualizar(Vehiculo vehiculo) {
        MapSqlParameterSource parameterSource = new MapSqlParameterSource();
        parameterSource.addValue(ID_VEHICULO, vehiculo.getIdVehiculo());
        parameterSource.addValue(ID_PROPIETARIO, vehiculo.getIdPropietario());
        parameterSource.addValue(PLACA, vehiculo.getPlaca());
        parameterSource.addValue(DESCRIPCION, vehiculo.getDescripcion());
        parameterSource.addValue(TIPO_VEHICULO, String.valueOf(vehiculo.getTipoVehiculo()));
        this.customNamedParameterJdbcTemplate.actualizar(parameterSource, sqlActualizarVehiculo);
    }

    @Override
    public void eliminar(Long id) {
        MapSqlParameterSource parameterSource = new MapSqlParameterSource();
        parameterSource.addValue(ID_VEHICULO, id);
        this.customNamedParameterJdbcTemplate.getNamedParameterJdbcTemplate().update(sqlEliminarVehiculo, parameterSource);
    }

    @Override
    public Boolean existePorId(Long id) {
        MapSqlParameterSource parameterSource = new MapSqlParameterSource();
        parameterSource.addValue(ID_VEHICULO, id);
        return this.customNamedParameterJdbcTemplate.getNamedParameterJdbcTemplate().queryForObject(sqlExisteVehiculoPorId,
                parameterSource, Boolean.class);
    }

    @Override
    public Boolean existePorPlaca(String placa) {
        MapSqlParameterSource parameterSource = new MapSqlParameterSource();
        parameterSource.addValue(PLACA, placa);
        return this.customNamedParameterJdbcTemplate.getNamedParameterJdbcTemplate().queryForObject(sqlExisteVehiculoPorPlaca,
                parameterSource, Boolean.class);
    }
}
