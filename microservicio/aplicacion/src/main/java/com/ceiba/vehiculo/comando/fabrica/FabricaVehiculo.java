package com.ceiba.vehiculo.comando.fabrica;

import com.ceiba.vehiculo.comando.ComandoVehiculo;
import com.ceiba.vehiculo.modelo.entidad.Vehiculo;
import org.springframework.stereotype.Component;

@Component
public class FabricaVehiculo {

    public Vehiculo guardar(ComandoVehiculo comandoVehiculo){
        return new Vehiculo(
                comandoVehiculo.getIdVehiculo(),
                comandoVehiculo.getIdPropietario(),
                comandoVehiculo.getPlaca(),
                comandoVehiculo.getDescripcion(),
                comandoVehiculo.getTipoVehiculo()
        );
    }
}
