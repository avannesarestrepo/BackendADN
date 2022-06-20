package com.ceiba.vehiculo.servicio;

import com.ceiba.dominio.excepcion.ExcepcionSinDatos;
import com.ceiba.vehiculo.puerto.repositorio.RepositorioVehiculo;

public class ServicioEliminarVehiculo {

    private static final String VEHICULO_NO_EXISTE = "El Vehiculo no existe en el sistema";

    private final RepositorioVehiculo repositorioVehiculo;

    public ServicioEliminarVehiculo(RepositorioVehiculo repositorioVehiculo) {
        this.repositorioVehiculo = repositorioVehiculo;
    }

    public void ejecutar(Long id){
        validarExistenciaPorId(id);
        repositorioVehiculo.eliminar(id);
    }

    private void validarExistenciaPorId(Long id){
        if(!repositorioVehiculo.existePorId(id)){
            throw new ExcepcionSinDatos(VEHICULO_NO_EXISTE);
        }
    }
}
