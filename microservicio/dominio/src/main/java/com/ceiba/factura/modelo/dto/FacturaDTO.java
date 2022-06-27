package com.ceiba.factura.modelo.dto;

import com.ceiba.factura.modelo.entidad.EstadoFactura;
import com.ceiba.factura.modelo.entidad.TipoModalidad;
import com.ceiba.vehiculo.modelo.dto.VehiculoDTO;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@AllArgsConstructor
@Getter
public class FacturaDTO {
    private Long id;
    private TipoModalidad tipoModalidad;
    private VehiculoDTO vehiculo;
    private LocalDateTime fechaInicio;
    private LocalDateTime fechaFin;
    private BigDecimal valor;
    private String placa;
    private EstadoFactura estadoFactura;
}
