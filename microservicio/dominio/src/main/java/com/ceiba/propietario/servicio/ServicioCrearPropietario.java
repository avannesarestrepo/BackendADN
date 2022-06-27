package com.ceiba.propietario.servicio;

import com.ceiba.dominio.excepcion.ExcepcionDuplicidad;
import com.ceiba.propietario.modelo.entidad.Propietario;
import com.ceiba.propietario.puerto.repositorio.RepositorioPropietario;

public class ServicioCrearPropietario {

    private static final String EXISTE_PROPIETARIO = "El propietario ya existe";

    private final RepositorioPropietario repositorioPropietario;

    public ServicioCrearPropietario(RepositorioPropietario repositorioPropietario) {
        this.repositorioPropietario = repositorioPropietario;
    }

    public Long ejecutar(Propietario propietario){
        validarExistenciaPropietario(propietario);
        return repositorioPropietario.crear(propietario);
    }

    private void validarExistenciaPropietario(Propietario propietario){
        if(repositorioPropietario.existePropietario(propietario)){
            throw new ExcepcionDuplicidad(EXISTE_PROPIETARIO);
        }
    }
}
