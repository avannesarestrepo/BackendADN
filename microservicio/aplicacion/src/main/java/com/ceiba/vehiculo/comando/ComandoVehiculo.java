package com.ceiba.vehiculo.comando;

import com.ceiba.propietario.modelo.entidad.Propietario;
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
    private Long id;
    private Propietario propietario;
    private String placa;
    private String descripcion;
    private TipoVehiculo tipoVehiculo;
}
