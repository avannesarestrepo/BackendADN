package com.ceiba.factura.servicio;

import com.ceiba.dominio.excepcion.ExcepcionSinDatos;
import com.ceiba.factura.puerto.repositorio.RepositorioFactura;

public class ServicioEliminarFactura {
    private static final String NOT_EXISTS = "La factura no existe en el sistema";
    private final RepositorioFactura repositorioFactura;

    public ServicioEliminarFactura(RepositorioFactura repositorioFactura) {
        this.repositorioFactura = repositorioFactura;
    }


    public void ejecutar(Long id){
        validarExistenciaPorId(id);
        repositorioFactura.eliminar(id);
    }

    private void validarExistenciaPorId(Long id) {
        if(!repositorioFactura.existeConId(id)){
            throw new ExcepcionSinDatos(NOT_EXISTS);
        }
    }
}
