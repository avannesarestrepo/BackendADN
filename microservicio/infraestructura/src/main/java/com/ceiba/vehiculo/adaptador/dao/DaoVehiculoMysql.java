package com.ceiba.vehiculo.adaptador.dao;

import com.ceiba.infraestructura.jdbc.CustomNamedParameterJdbcTemplate;
import com.ceiba.infraestructura.jdbc.EjecucionBaseDeDatos;
import com.ceiba.infraestructura.jdbc.sqlstatement.SqlStatement;
import com.ceiba.vehiculo.modelo.dto.VehiculoDTO;
import com.ceiba.vehiculo.puerto.dao.DaoVehiculo;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.stereotype.Repository;

@Repository
public class DaoVehiculoMysql implements DaoVehiculo {

    private final CustomNamedParameterJdbcTemplate customNamedParameterJdbcTemplate;

    public DaoVehiculoMysql(CustomNamedParameterJdbcTemplate customNamedParameterJdbcTemplate) {
        this.customNamedParameterJdbcTemplate = customNamedParameterJdbcTemplate;
    }

    @SqlStatement(namespace = "vehiculo", value="obtenervehiculoporid")
    private static String sqlObtenerVehiculoPorId;

    @SqlStatement(namespace = "vehiculo", value = "obtenervehiculoporplaca")
    private static String sqlObtenerVehiculoPorPlaca;

    @SqlStatement(namespace = "vehiculo", value = "existevehiculoporid")
    private static String sqlExisteVehiculoPorId;

    @SqlStatement(namespace = "vehiculo", value = "existevehiculoporplaca")
    private static String sqlExisteVehiculoPorPlaca;

    @Override
    public Boolean existePorId(Long id) {
        MapSqlParameterSource parameterSource = new MapSqlParameterSource();
        parameterSource.addValue("id_vehiculo", id);
        return this.customNamedParameterJdbcTemplate.getNamedParameterJdbcTemplate().queryForObject(sqlExisteVehiculoPorId,
                parameterSource, Boolean.class);
    }

    @Override
    public Boolean existePorPlaca(String placa) {
        MapSqlParameterSource parameterSource = new MapSqlParameterSource();
        parameterSource.addValue("placa", placa);
        return this.customNamedParameterJdbcTemplate.getNamedParameterJdbcTemplate().queryForObject(sqlExisteVehiculoPorPlaca,
                parameterSource, Boolean.class);
    }

    @Override
    public VehiculoDTO obtenerPorId(Long id) {
        MapSqlParameterSource parameterSource = new MapSqlParameterSource();
        parameterSource.addValue("id_vehiculo", id);
        return EjecucionBaseDeDatos.obtenerUnObjetoONull(() ->
                this.customNamedParameterJdbcTemplate.getNamedParameterJdbcTemplate().queryForObject(sqlObtenerVehiculoPorId,
                        parameterSource, new MapeoVehiculo()));
    }

    @Override
    public VehiculoDTO obtenerPorPlaca(String placa) {
        MapSqlParameterSource parameterSource = new MapSqlParameterSource();
        parameterSource.addValue("placa", placa);
        return EjecucionBaseDeDatos.obtenerUnObjetoONull(() ->
                this.customNamedParameterJdbcTemplate.getNamedParameterJdbcTemplate().queryForObject(sqlObtenerVehiculoPorPlaca,
                        parameterSource, new MapeoVehiculo()));
    }
}
