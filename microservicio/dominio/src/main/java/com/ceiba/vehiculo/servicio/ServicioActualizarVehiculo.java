package com.ceiba.vehiculo.servicio;

import com.ceiba.dominio.excepcion.ExcepcionSinDatos;
import com.ceiba.propietario.puerto.repositorio.RepositorioPropietario;
import com.ceiba.vehiculo.modelo.entidad.Vehiculo;
import com.ceiba.vehiculo.puerto.repositorio.RepositorioVehiculo;

public class ServicioActualizarVehiculo {

    private static final String VEHICULO_NO_EXISTE = "El vehiculo no existe en el sistema";

    private static final String PROPIETARIO_NO_EXISTE = "El propietario asociado no existe";

    private final RepositorioVehiculo repositorioVehiculo;

    private final RepositorioPropietario repositorioPropietario;

    public ServicioActualizarVehiculo(RepositorioVehiculo repositorioVehiculo, RepositorioPropietario repositorioPropietario) {
        this.repositorioVehiculo = repositorioVehiculo;
        this.repositorioPropietario = repositorioPropietario;
    }

    public void ejecutar(Vehiculo vehiculo){
        validarExistenciaPorId(vehiculo);
        validarExistenciaPropietarioPorId(vehiculo);
        repositorioVehiculo.actualizar(vehiculo);
    }

    private void validarExistenciaPorId(Vehiculo vehiculo){
        if(!repositorioVehiculo.existePorId(vehiculo.getIdVehiculo())){
            throw new ExcepcionSinDatos(VEHICULO_NO_EXISTE);
        }
    }

    private void validarExistenciaPropietarioPorId(Vehiculo vehiculo){
        if(!repositorioPropietario.existeConId(vehiculo.getIdPropietario())){
            throw new ExcepcionSinDatos(PROPIETARIO_NO_EXISTE);
        }
    }
}
