package com.ceiba.factura.adaptador.dao;

import com.ceiba.factura.modelo.dto.FacturaDTO;
import com.ceiba.factura.modelo.entidad.TipoModalidadFactura;
import com.ceiba.infraestructura.jdbc.MapperResult;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDateTime;

@Component
public class MapeoFactura implements RowMapper<FacturaDTO>, MapperResult {
    @Override
    public FacturaDTO mapRow(ResultSet rs, int rowNum) throws SQLException {
        Long idFactura = rs.getLong("id_factura");
        TipoModalidadFactura tipoModalidadFactura = TipoModalidadFactura.valueOf(rs.getString("tipo_modalidad"));
        Long idPropietario = rs.getLong("id_propietario");
        Long idVehiculo = rs.getLong("id_vehiculo");
        LocalDateTime fechaInicio = rs.getTimestamp("fecha_inicio").toLocalDateTime();
        LocalDateTime fechaFin = rs.getTimestamp("fecha_fin").toLocalDateTime();
        Double valor = rs.getDouble("valor");
        String descripcion = rs.getString("descripcion");
        Boolean estado = rs.getBoolean("estado");
        return new FacturaDTO(idFactura, tipoModalidadFactura, idPropietario, idVehiculo,
                fechaInicio, fechaFin, valor, descripcion, estado);
    }
}
