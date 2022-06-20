package com.ceiba.vehiculo.servicio;

import com.ceiba.dominio.excepcion.ExcepcionDuplicidad;
import com.ceiba.dominio.excepcion.ExcepcionSinDatos;
import com.ceiba.propietario.puerto.repositorio.RepositorioPropietario;
import com.ceiba.vehiculo.modelo.entidad.Vehiculo;
import com.ceiba.vehiculo.puerto.repositorio.RepositorioVehiculo;

public class ServicioCrearVehiculo {
    private static final String EXISTE = "La vehiculo ya existe en el sistema";

    private static final String PROPIETARIO_NO_EXISTE = "El propietario asociado no existe";

    private final RepositorioVehiculo repositorioVehiculo;

    private final RepositorioPropietario repositorioPropietario;

    public ServicioCrearVehiculo(RepositorioVehiculo repositorioVehiculo, RepositorioPropietario repositorioPropietario) {
        this.repositorioVehiculo = repositorioVehiculo;
        this.repositorioPropietario = repositorioPropietario;
    }

    public Long execute(Vehiculo vehiculo){
        validarExistenciaPropietarioPorId(vehiculo);
        validarExistenciaPorPlaca(vehiculo);
        return repositorioVehiculo.guardar(vehiculo);
    }

    private void validarExistenciaPorPlaca(Vehiculo vehiculo){
        if(repositorioVehiculo.existePorPlaca(vehiculo.getPlaca())){
            throw new ExcepcionDuplicidad(EXISTE);
        }
    }

    private void validarExistenciaPropietarioPorId(Vehiculo vehiculo){
        if(!repositorioPropietario.existeConId(vehiculo.getIdPropietario())){
            throw new ExcepcionSinDatos(PROPIETARIO_NO_EXISTE);
        }
    }
}
