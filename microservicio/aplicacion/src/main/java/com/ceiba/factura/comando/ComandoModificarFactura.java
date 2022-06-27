package com.ceiba.factura.comando;

import com.ceiba.factura.modelo.entidad.EstadoFactura;
import com.ceiba.factura.modelo.entidad.TipoModalidad;
import com.ceiba.vehiculo.modelo.entidad.Vehiculo;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ComandoModificarFactura {
    private Long id;
    private TipoModalidad tipoModalidad;
    private Vehiculo vehiculo;
    private String fechaInicio;
    private String fechaFin;
    private BigDecimal valor;
    private String placa;
    private EstadoFactura estadoFactura;
}
