package com.ceiba.propietario.servicio;

import com.ceiba.dominio.excepcion.ExcepcionSinDatos;
import com.ceiba.propietario.modelo.entidad.Propietario;
import com.ceiba.propietario.puerto.repositorio.RepositorioPropietario;

public class ServicioActualizarPropietario {

    private static final String NOT_EXISTS = "El propietario no existe en el sistema";

    private final RepositorioPropietario repositorioPropietario;

    public ServicioActualizarPropietario(RepositorioPropietario repositorioPropietario) {
        this.repositorioPropietario = repositorioPropietario;
    }

    public void ejecutar(Propietario propietario){
        validarExistenciaPorId(propietario);
        repositorioPropietario.actualizar(propietario);
    }

    private void validarExistenciaPorId(Propietario propietario) {
        if(!repositorioPropietario.existeConId(propietario.getId())){
            throw new ExcepcionSinDatos(NOT_EXISTS);
        }
    }
}
