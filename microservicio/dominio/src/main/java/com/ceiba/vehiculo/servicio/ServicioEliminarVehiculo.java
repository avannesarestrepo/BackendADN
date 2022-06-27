package com.ceiba.vehiculo.servicio;

import com.ceiba.vehiculo.puerto.repositorio.RepositorioVehiculo;

public class ServicioEliminarVehiculo {

    private final RepositorioVehiculo repositorioVehiculo;

    public ServicioEliminarVehiculo(RepositorioVehiculo repositorioVehiculo) {
        this.repositorioVehiculo = repositorioVehiculo;
    }

    public void ejecutar(String placa){
        repositorioVehiculo.eliminar(placa);
    }
}
