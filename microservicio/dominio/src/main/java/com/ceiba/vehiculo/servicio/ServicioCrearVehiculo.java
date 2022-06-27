package com.ceiba.vehiculo.servicio;

import com.ceiba.dominio.excepcion.ExcepcionDuplicidad;
import com.ceiba.vehiculo.modelo.entidad.Vehiculo;
import com.ceiba.vehiculo.puerto.repositorio.RepositorioVehiculo;

public class ServicioCrearVehiculo {

    private static final String VEHICULO_EXISTE = "El Vehiculo existe en el sistema";

    private final RepositorioVehiculo repositorioVehiculo;

    public ServicioCrearVehiculo(RepositorioVehiculo repositorioVehiculo) {
        this.repositorioVehiculo = repositorioVehiculo;
    }

    public Long ejecutar(Vehiculo vehiculo){
        validarExistenciaVehiculoPorPlaca(vehiculo);
        return repositorioVehiculo.crear(vehiculo);
    }

    private void validarExistenciaVehiculoPorPlaca(Vehiculo vehiculo){
        if(repositorioVehiculo.existeVehiculo(vehiculo)){
            throw new ExcepcionDuplicidad(VEHICULO_EXISTE);
        }
    }
}
