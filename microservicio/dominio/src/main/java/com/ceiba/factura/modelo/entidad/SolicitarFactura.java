package com.ceiba.factura.modelo.entidad;

import com.ceiba.vehiculo.modelo.entidad.Vehiculo;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
@AllArgsConstructor
public class SolicitarFactura {
    private TipoModalidad tipoModalidad;
    private Vehiculo vehiculo;
    private LocalDateTime fechaInicio;
    private String placa;
}
