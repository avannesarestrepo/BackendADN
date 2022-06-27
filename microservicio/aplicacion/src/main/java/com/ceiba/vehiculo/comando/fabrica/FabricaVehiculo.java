package com.ceiba.vehiculo.comando.fabrica;

import com.ceiba.propietario.puerto.repositorio.RepositorioPropietario;
import com.ceiba.vehiculo.comando.ComandoVehiculo;
import com.ceiba.vehiculo.modelo.entidad.Vehiculo;
import org.springframework.stereotype.Component;

@Component
public class FabricaVehiculo {

    private final RepositorioPropietario repositorioPropietario;

    public FabricaVehiculo(RepositorioPropietario repositorioPropietario) {
        this.repositorioPropietario = repositorioPropietario;
    }

    public Vehiculo crear(ComandoVehiculo comandoVehiculo){
        return new Vehiculo(
                comandoVehiculo.getId(),
                repositorioPropietario.obtenerPropietario(comandoVehiculo.getPropietario()),
                comandoVehiculo.getPlaca(),
                comandoVehiculo.getDescripcion(),
                comandoVehiculo.getTipoVehiculo()
        );
    }
}
