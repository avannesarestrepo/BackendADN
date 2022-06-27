package com.ceiba.vehiculo.adaptador.repositorio;

import com.ceiba.infraestructura.jdbc.MapperResult;
import com.ceiba.propietario.puerto.repositorio.RepositorioPropietario;
import com.ceiba.vehiculo.modelo.entidad.TipoVehiculo;
import com.ceiba.vehiculo.modelo.entidad.Vehiculo;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;

import java.sql.ResultSet;
import java.sql.SQLException;

@Component
public class MapeoVehiculo implements RowMapper<Vehiculo>, MapperResult {

    private final RepositorioPropietario repositorioPropietario;

    public MapeoVehiculo(RepositorioPropietario repositorioPropietario) {
        this.repositorioPropietario = repositorioPropietario;
    }


    @Override
    public Vehiculo mapRow(ResultSet rs, int rowNum) throws SQLException {
        Long id = rs.getLong("id");
        Long idPropietario = rs.getLong("id_propietario");
        String placa = rs.getString("placa");
        String descripcion = rs.getString("descripcion");
        TipoVehiculo tipoVehiculo = TipoVehiculo.valueOf(rs.getString("tipo_vehiculo"));
        return new Vehiculo(id, repositorioPropietario.obtenerPorId(idPropietario) , placa, descripcion, tipoVehiculo);
    }
}
