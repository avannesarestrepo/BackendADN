package com.ceiba.propietario.servicio;

import com.ceiba.dominio.excepcion.ExcepcionSinDatos;
import com.ceiba.propietario.puerto.repositorio.RepositorioPropietario;

public class ServiciosEliminarPropietario {

    private static final String NOT_EXISTS = "El propietario no existe en el sistema";
    private final RepositorioPropietario repositorioPropietario;

    public ServiciosEliminarPropietario(RepositorioPropietario repositorioPropietario) {
        this.repositorioPropietario = repositorioPropietario;
    }

    public void ejecutar(Long id){
        validarExistenciaPorId(id);
        repositorioPropietario.eliminar(id);
    }

    private void validarExistenciaPorId(Long id) {
        if(!repositorioPropietario.existeConId(id)){
            throw new ExcepcionSinDatos(NOT_EXISTS);
        }
    }
}
