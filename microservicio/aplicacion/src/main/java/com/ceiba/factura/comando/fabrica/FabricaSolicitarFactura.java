package com.ceiba.factura.comando.fabrica;

import com.ceiba.factura.comando.ComandoModificarFactura;
import com.ceiba.factura.comando.ComandoSolicitarFactura;
import com.ceiba.factura.modelo.entidad.Factura;
import com.ceiba.factura.modelo.entidad.SolicitarFactura;
import com.ceiba.factura.modelo.entidad.TipoModalidad;
import com.ceiba.vehiculo.puerto.repositorio.RepositorioVehiculo;
import org.springframework.stereotype.Component;

import java.sql.Timestamp;

@Component
public class FabricaSolicitarFactura {

    private final RepositorioVehiculo repositorioVehiculo;

    public FabricaSolicitarFactura(RepositorioVehiculo repositorioVehiculo) {
        this.repositorioVehiculo = repositorioVehiculo;
    }

    public SolicitarFactura crear(ComandoSolicitarFactura comandoSolicitarFactura){
        return new SolicitarFactura(
                comandoSolicitarFactura.getTipoModalidad(),
                (comandoSolicitarFactura.getTipoModalidad().equals(TipoModalidad.MENSUAL) || comandoSolicitarFactura.getTipoModalidad().equals(TipoModalidad.QUINCENAL)) && comandoSolicitarFactura.getVehiculo() != null ? repositorioVehiculo.obtenerVehiculo(comandoSolicitarFactura.getVehiculo()): null,
                Timestamp.valueOf(comandoSolicitarFactura.getFechaInicio()).toLocalDateTime(),
                comandoSolicitarFactura.getPlaca()
        );
    }

    public Factura modificar(ComandoModificarFactura comandoModificarFactura){
        return new Factura(
                comandoModificarFactura.getId(),
                comandoModificarFactura.getTipoModalidad(),
                comandoModificarFactura.getVehiculo(),
                Timestamp.valueOf(comandoModificarFactura.getFechaInicio()).toLocalDateTime(),
                comandoModificarFactura.getPlaca(),
                comandoModificarFactura.getEstadoFactura()
        );
    }
}
