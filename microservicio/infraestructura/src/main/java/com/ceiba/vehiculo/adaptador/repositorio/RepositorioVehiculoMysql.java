package com.ceiba.vehiculo.adaptador.repositorio;

import com.ceiba.infraestructura.jdbc.CustomNamedParameterJdbcTemplate;
import com.ceiba.infraestructura.jdbc.sqlstatement.SqlStatement;
import com.ceiba.vehiculo.modelo.entidad.Vehiculo;
import com.ceiba.vehiculo.puerto.repositorio.RepositorioVehiculo;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.stereotype.Repository;

@Repository
public class RepositorioVehiculoMysql implements RepositorioVehiculo {

    private static final String ID = "id";
    private static final String ID_PROPIETARIO = "id_propietario";
    private static final String PLACA = "placa";
    private static final String DESCRIPCION = "descripcion";
    private static final String TIPO_VEHICULO = "tipo_vehiculo";

    private final CustomNamedParameterJdbcTemplate customNamedParameterJdbcTemplate;

    private final MapeoVehiculo mapeoVehiculo;

    @SqlStatement(namespace = "vehiculo", value = "crearvehiculo")
    private static String sqlCrearVehiculo;

    @SqlStatement(namespace = "vehiculo", value = "actualizarvehiculo")
    private static String sqlActualizarVehiculo;

    @SqlStatement(namespace = "vehiculo", value = "eliminarvehiculo")
    private static String sqlEliminarVehiculo;

    @SqlStatement(namespace = "vehiculo", value = "existevehiculo")
    private static String sqlExisteVehiculo;

    @SqlStatement(namespace = "vehiculo", value = "obtenervehiculoporid")
    private static String sqlObtenerVehiculo;

    public RepositorioVehiculoMysql(CustomNamedParameterJdbcTemplate customNamedParameterJdbcTemplate, MapeoVehiculo mapeoVehiculo) {
        this.customNamedParameterJdbcTemplate = customNamedParameterJdbcTemplate;
        this.mapeoVehiculo = mapeoVehiculo;
    }

    @Override
    public Long crear(Vehiculo vehiculo) {
        MapSqlParameterSource parameterSource = new MapSqlParameterSource();
        parameterSource.addValue(ID, vehiculo.getId());
        parameterSource.addValue(ID_PROPIETARIO, vehiculo.getPropietario().getId());
        parameterSource.addValue(PLACA, vehiculo.getPlaca());
        parameterSource.addValue(DESCRIPCION, vehiculo.getDescripcion());
        parameterSource.addValue(TIPO_VEHICULO, String.valueOf(vehiculo.getTipoVehiculo()));
        return this.customNamedParameterJdbcTemplate.crear(parameterSource, sqlCrearVehiculo);
    }

    @Override
    public void actualizar(Vehiculo vehiculo) {
        MapSqlParameterSource parameterSource = new MapSqlParameterSource();
        parameterSource.addValue(ID, vehiculo.getId());
        parameterSource.addValue(ID_PROPIETARIO, vehiculo.getPropietario().getId());
        parameterSource.addValue(PLACA, vehiculo.getPlaca());
        parameterSource.addValue(DESCRIPCION, vehiculo.getDescripcion());
        parameterSource.addValue(TIPO_VEHICULO, String.valueOf(vehiculo.getTipoVehiculo()));
        this.customNamedParameterJdbcTemplate.actualizar(parameterSource, sqlActualizarVehiculo);
    }

    @Override
    public void eliminar(String placa) {
        MapSqlParameterSource parameterSource = new MapSqlParameterSource();
        parameterSource.addValue(PLACA, placa);
        this.customNamedParameterJdbcTemplate.getNamedParameterJdbcTemplate()
                .update(sqlEliminarVehiculo, parameterSource);

    }

    @Override
    public Boolean existeVehiculo(Vehiculo vehiculo) {
        MapSqlParameterSource parameterSource = new MapSqlParameterSource();
        parameterSource.addValue(ID, vehiculo.getId());
        return this.customNamedParameterJdbcTemplate.getNamedParameterJdbcTemplate()
                .queryForObject(sqlExisteVehiculo, parameterSource, Boolean.class);
    }

    @Override
    public Vehiculo obtenerVehiculo(Vehiculo vehiculo) {
        MapSqlParameterSource parameterSource = new MapSqlParameterSource();
        parameterSource.addValue(ID, vehiculo.getId());
        return this.customNamedParameterJdbcTemplate.getNamedParameterJdbcTemplate()
                .queryForObject(sqlObtenerVehiculo, parameterSource, mapeoVehiculo);
    }

    @Override
    public Vehiculo obtenerVehiculoPorId(Long id){
        MapSqlParameterSource parameterSource = new MapSqlParameterSource();
        parameterSource.addValue(ID, id);
        return this.customNamedParameterJdbcTemplate.getNamedParameterJdbcTemplate()
                .queryForObject(sqlObtenerVehiculo, parameterSource, mapeoVehiculo);
    }
}
