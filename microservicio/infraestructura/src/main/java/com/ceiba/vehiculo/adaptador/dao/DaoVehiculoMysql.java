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
    private final MapeoVehiculoDTO mapeoVehiculo;

    @SqlStatement(namespace = "vehiculo", value = "obtenervehiculoporid")
    private static String sqlObtenerVehiculo;

    @SqlStatement(namespace = "vehiculo", value = "obtenervehiculoporplaca")
    private static String sqlObtenerVehiculoPorPlaca;

    public DaoVehiculoMysql(CustomNamedParameterJdbcTemplate customNamedParameterJdbcTemplate, MapeoVehiculoDTO mapeoVehiculo) {
        this.customNamedParameterJdbcTemplate = customNamedParameterJdbcTemplate;
        this.mapeoVehiculo = mapeoVehiculo;
    }

    @Override
    public VehiculoDTO obtenerVehiculoPorId(Long id) {
        MapSqlParameterSource parameterSource = new MapSqlParameterSource();
        parameterSource.addValue("id", id);
        return EjecucionBaseDeDatos.obtenerUnObjetoONull(() ->this.customNamedParameterJdbcTemplate.getNamedParameterJdbcTemplate()
                .queryForObject(sqlObtenerVehiculo, parameterSource, mapeoVehiculo));
    }

    @Override
    public VehiculoDTO obtenerVehiculoPorPlaca(String placa) {
        MapSqlParameterSource parameterSource = new MapSqlParameterSource();
        parameterSource.addValue("placa", placa);
        return EjecucionBaseDeDatos.obtenerUnObjetoONull(() ->
                this.customNamedParameterJdbcTemplate.getNamedParameterJdbcTemplate()
                        .queryForObject(sqlObtenerVehiculoPorPlaca, parameterSource, mapeoVehiculo));
    }
}
