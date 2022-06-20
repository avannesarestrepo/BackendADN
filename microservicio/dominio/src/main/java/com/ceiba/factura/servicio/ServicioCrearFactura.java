package com.ceiba.factura.servicio;

import com.ceiba.dominio.excepcion.ExcepcionSinDatos;
import com.ceiba.dominio.excepcion.ExcepcionValorObligatorio;
import com.ceiba.factura.modelo.entidad.Factura;
import com.ceiba.factura.modelo.entidad.TipoModalidadFactura;
import com.ceiba.factura.puerto.repositorio.RepositorioFactura;
import com.ceiba.propietario.puerto.dao.DaoPropietario;
import com.ceiba.vehiculo.puerto.dao.DaoVehiculo;

public class ServicioCrearFactura {

    private static final String NO_EXISTE_PROPIETARIO= "El propietario relacionado a la factura no existe";
    private static final String NO_EXISTE_VEHICULO= "El vehiculo relacionado a la factura no existe";


    private final RepositorioFactura repositorioFactura;
    private final DaoVehiculo daoVehiculo;
    private final DaoPropietario daoPropietario;

    public ServicioCrearFactura(RepositorioFactura repositorioFactura, DaoVehiculo daoVehiculo, DaoPropietario daoPropietario) {
        this.repositorioFactura = repositorioFactura;
        this.daoVehiculo = daoVehiculo;
        this.daoPropietario = daoPropietario;
    }

    public Long ejecutar(Factura factura){
        validarCamposRequeridos(factura);
        return repositorioFactura.guardar(factura);
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
}
