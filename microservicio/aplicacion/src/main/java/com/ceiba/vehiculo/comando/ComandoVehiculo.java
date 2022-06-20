package com.ceiba.vehiculo.comando;

import com.ceiba.vehiculo.modelo.entidad.TipoVehiculo;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ComandoVehiculo {
    private Long idVehiculo;
    private Long idPropietario;
    private String placa;
    private String descripcion;
    private TipoVehiculo tipoVehiculo;
}
