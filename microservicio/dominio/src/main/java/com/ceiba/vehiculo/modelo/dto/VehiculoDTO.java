package com.ceiba.vehiculo.modelo.dto;

import com.ceiba.propietario.modelo.dto.PropietarioDTO;
import com.ceiba.vehiculo.modelo.entidad.TipoVehiculo;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class VehiculoDTO {
    private Long id;
    private PropietarioDTO propietarioDTO;
    private String placa;
    private String descripcion;
    private TipoVehiculo tipoVehiculo;
}
