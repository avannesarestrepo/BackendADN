package com.ceiba.propietario.servicio;

import com.ceiba.dominio.excepcion.ExcepcionSinDatos;
import com.ceiba.propietario.modelo.entidad.Propietario;
import com.ceiba.propietario.puerto.repositorio.RepositorioPropietario;

public class ServicioActualizarPropietario {

    private static final String NO_EXISTE_PROPIETARIO = "El propietario no existe en el sistema";

    private final RepositorioPropietario repositorioPropietario;

    public ServicioActualizarPropietario(RepositorioPropietario repositorioPropietario) {
        this.repositorioPropietario = repositorioPropietario;
    }

    public void ejecutar(Propietario propietario){
        validarExistenciaPorDocumento(propietario);
        repositorioPropietario.actualizar(propietario);
    }

    private void validarExistenciaPorDocumento(Propietario propietario) {
        if(!repositorioPropietario.existePropietario(propietario)){
            throw new ExcepcionSinDatos(NO_EXISTE_PROPIETARIO);
        }
    }
}
