package com.ceiba.propietario.servicio;

import com.ceiba.dominio.excepcion.ExcepcionDuplicidad;
import com.ceiba.propietario.modelo.entidad.Propietario;
import com.ceiba.propietario.puerto.repositorio.RepositorioPropietario;

public class ServicioCrearPropietario {

    private static final String EXISTE = "El propietario ya existe";

    private final RepositorioPropietario repositorioPropietario;

    public ServicioCrearPropietario(RepositorioPropietario repositorioPropietario) {
        this.repositorioPropietario = repositorioPropietario;
    }

    public Long ejecutar(Propietario propietario){
        validarExistenciaPorDocumento(propietario);
        return repositorioPropietario.guardar(propietario);
    }

    private void validarExistenciaPorDocumento(Propietario propietario){
        if(repositorioPropietario.existeConDocumento(propietario.getDocumento())){
            throw new ExcepcionDuplicidad(EXISTE);
        }
    }
}
