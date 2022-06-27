package com.ceiba.vehiculo.servicio;

import com.ceiba.dominio.excepcion.ExcepcionSinDatos;
import com.ceiba.vehiculo.modelo.entidad.Vehiculo;
import com.ceiba.vehiculo.puerto.repositorio.RepositorioVehiculo;

public class ServicioActualizarVehiculo {

    private static final String VEHICULO_NO_EXISTE = "El vehiculo no existe en el sistema";

    private final RepositorioVehiculo repositorioVehiculo;

    public ServicioActualizarVehiculo(RepositorioVehiculo repositorioVehiculo) {
        this.repositorioVehiculo = repositorioVehiculo;
    }

    public void ejecutar(Vehiculo vehiculo){
        validarExistenciaVehiculoPorPlaca(vehiculo);
        repositorioVehiculo.actualizar(vehiculo);
    }

    private void validarExistenciaVehiculoPorPlaca(Vehiculo vehiculo) {
        if(!repositorioVehiculo.existeVehiculo(vehiculo)){
            throw new ExcepcionSinDatos(VEHICULO_NO_EXISTE);
        }
    }
}
