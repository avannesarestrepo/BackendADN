package com.ceiba.factura.comando;

import com.ceiba.factura.modelo.entidad.Factura;
import com.ceiba.factura.modelo.entidad.TipoModalidadFactura;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ComandoFactura {
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
