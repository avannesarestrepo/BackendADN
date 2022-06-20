package com.ceiba.vehiculo.adaptador.dao;

import com.ceiba.infraestructura.jdbc.MapperResult;
import com.ceiba.vehiculo.modelo.dto.VehiculoDTO;
import com.ceiba.vehiculo.modelo.entidad.TipoVehiculo;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;

import java.sql.ResultSet;
import java.sql.SQLException;

@Component
public class MapeoVehiculo implements RowMapper<VehiculoDTO>, MapperResult {
    @Override
    public VehiculoDTO mapRow(ResultSet rs, int rowNum) throws SQLException {
        Long idVehiculo = rs.getLong("id_vehiculo");
        Long idPropietario = rs.getLong("id_propietario");
        String placa = rs.getString("placa");
        String descripcion = rs.getString("descripcion");
        TipoVehiculo tipoVehiculo = TipoVehiculo.valueOf(rs.getString("tipo_vehiculo"));

        return new VehiculoDTO(idVehiculo, idPropietario, placa, descripcion, tipoVehiculo);
    }
}
