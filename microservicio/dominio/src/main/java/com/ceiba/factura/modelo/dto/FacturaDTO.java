package com.ceiba.factura.modelo.dto;

import com.ceiba.factura.modelo.entidad.TipoModalidadFactura;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
@AllArgsConstructor
public class FacturaDTO {
    private Long idFactura;
    private TipoModalidadFactura tipoModalidad;
    private Long idPropietario;
    private Long idVehiculo;
    private LocalDateTime fechaInicio;
    private LocalDateTime fechaFin;
    private Double valor;
    private String descripcion;
    private Boolean estado;
}
