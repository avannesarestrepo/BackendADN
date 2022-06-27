package com.ceiba.factura.adaptador.repositorio;

import com.ceiba.factura.modelo.entidad.EstadoFactura;
import com.ceiba.factura.modelo.entidad.Factura;
import com.ceiba.factura.modelo.entidad.TipoModalidad;
import com.ceiba.infraestructura.jdbc.MapperResult;
import com.ceiba.vehiculo.modelo.entidad.Vehiculo;
import com.ceiba.vehiculo.puerto.repositorio.RepositorioVehiculo;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDateTime;

@Component
public class MapeoFactura implements RowMapper<Factura>, MapperResult {

    private final RepositorioVehiculo repositorioVehiculo;

    public MapeoFactura(RepositorioVehiculo repositorioVehiculo) {
        this.repositorioVehiculo = repositorioVehiculo;
    }

    @Override
    public Factura mapRow(ResultSet rs, int rowNum) throws SQLException {
        Long id = rs.getLong("id");
        TipoModalidad tipoModalidad = TipoModalidad.valueOf(rs.getString("tipo_modalidad"));
        Vehiculo vehiculo = repositorioVehiculo.obtenerVehiculoPorId(rs.getLong("id_vehiculo"));
        LocalDateTime fechaInicio =rs.getTimestamp("fecha_inicio").toLocalDateTime();
        LocalDateTime fechaFin =rs.getTimestamp("fecha_fin").toLocalDateTime();
        BigDecimal valor = BigDecimal.valueOf(rs.getDouble("valor"));
        String placa = rs.getString("placa");
        EstadoFactura estado = EstadoFactura.valueOf(rs.getString("estado"));
        return new Factura(id, tipoModalidad, vehiculo, fechaInicio, fechaFin, valor, placa, estado);
    }
}
