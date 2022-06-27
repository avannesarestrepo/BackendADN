package com.ceiba.propietario.servicio;

import com.ceiba.propietario.puerto.repositorio.RepositorioPropietario;

public class ServicioEliminarPropietario {
    private final RepositorioPropietario repositorioPropietario;

    public ServicioEliminarPropietario(RepositorioPropietario repositorioPropietario) {
        this.repositorioPropietario = repositorioPropietario;
    }

    public void ejecutar(String documento){
        repositorioPropietario.eliminar(documento);
    }
}
