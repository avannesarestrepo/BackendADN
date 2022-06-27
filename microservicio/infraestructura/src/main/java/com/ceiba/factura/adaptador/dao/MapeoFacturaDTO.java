package com.ceiba.factura.adaptador.dao;

import com.ceiba.factura.modelo.dto.FacturaDTO;
import com.ceiba.factura.modelo.entidad.EstadoFactura;
import com.ceiba.factura.modelo.entidad.TipoModalidad;
import com.ceiba.infraestructura.jdbc.MapperResult;
import com.ceiba.vehiculo.modelo.dto.VehiculoDTO;
import com.ceiba.vehiculo.puerto.dao.DaoVehiculo;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDateTime;

@Component
public class MapeoFacturaDTO implements RowMapper<FacturaDTO>, MapperResult {

    private final DaoVehiculo daoVehiculo;

    public MapeoFacturaDTO(DaoVehiculo daoVehiculo) {
        this.daoVehiculo = daoVehiculo;
    }

    @Override
    public FacturaDTO mapRow(ResultSet rs, int rowNum) throws SQLException {
        Long id = rs.getLong("id");
        TipoModalidad tipoModalidad = TipoModalidad.valueOf(rs.getString("tipo_modalidad"));
        VehiculoDTO vehiculo = daoVehiculo.obtenerVehiculoPorId(rs.getLong("id_vehiculo"));
        LocalDateTime fechaInicio =rs.getTimestamp("fecha_inicio").toLocalDateTime();
        LocalDateTime fechaFin =rs.getTimestamp("fecha_fin").toLocalDateTime();
        BigDecimal valor = BigDecimal.valueOf(rs.getDouble("valor"));
        String placa = rs.getString("placa");
        EstadoFactura estado = EstadoFactura.valueOf(rs.getString("estado"));
        return new FacturaDTO(id, tipoModalidad, vehiculo, fechaInicio, fechaFin, valor, placa, estado);
    }
}
