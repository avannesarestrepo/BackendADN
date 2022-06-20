package com.ceiba.factura.servicio;

import com.ceiba.dominio.excepcion.ExcepcionSinDatos;
import com.ceiba.dominio.excepcion.ExcepcionValorObligatorio;
import com.ceiba.factura.modelo.entidad.Factura;
import com.ceiba.factura.modelo.entidad.TipoModalidadFactura;
import com.ceiba.factura.puerto.repositorio.RepositorioFactura;
import com.ceiba.propietario.puerto.dao.DaoPropietario;
import com.ceiba.vehiculo.puerto.dao.DaoVehiculo;

public class ServicioActualizarFactura {

    private static final String NO_EXISTE_PROPIETARIO= "El propietario relacionado a la factura no existe";
    private static final String NO_EXISTE_VEHICULO= "El vehiculo relacionado a la factura no existe";
    private static final String NOT_EXISTS = "La factura no existe en el sistema";

    private final RepositorioFactura repositorioFactura;
    private final DaoVehiculo daoVehiculo;
    private final DaoPropietario daoPropietario;

    public ServicioActualizarFactura(RepositorioFactura repositorioFactura, DaoVehiculo daoVehiculo, DaoPropietario daoPropietario) {
        this.repositorioFactura = repositorioFactura;
        this.daoVehiculo = daoVehiculo;
        this.daoPropietario = daoPropietario;
    }


    public void ejecutar(Factura factura){
        validarExistenciaPorId(factura);
        validarCamposRequeridos(factura);
        repositorioFactura.actualizar(factura);
    }

    public void validarCamposRequeridos(Factura factura){
        if(factura.getTipoModalidad().equals(TipoModalidadFactura.QUINCENAL) || factura.getTipoModalidad().equals(TipoModalidadFactura.MENSUAL)){
            if(!daoPropietario.existePorId(factura.getIdPropietario())){
                throw new ExcepcionSinDatos(NO_EXISTE_PROPIETARIO);
            }

            if(!daoVehiculo.existePorId(factura.getIdVehiculo())){
                throw new ExcepcionSinDatos(NO_EXISTE_VEHICULO);
            }
        }

        if((factura.getTipoModalidad().equals(TipoModalidadFactura.HORA) || factura.getTipoModalidad().equals(TipoModalidadFactura.DIA))
                && (factura.getDescripcion() == null || factura.getDescripcion().isEmpty())){
                throw new ExcepcionValorObligatorio("El campo 'descripcion' para la modalidad asignada es obligatorio");
        }
    }

    public void validarExistenciaPorId(Factura factura){
        if(!repositorioFactura.existeConId(factura.getIdFactura())){
            throw new ExcepcionSinDatos(NOT_EXISTS);
        }
    }
}
