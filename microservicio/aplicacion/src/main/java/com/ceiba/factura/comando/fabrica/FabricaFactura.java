package com.ceiba.factura.comando.fabrica;

import com.ceiba.factura.comando.ComandoFactura;
import com.ceiba.factura.modelo.entidad.Factura;
import org.springframework.stereotype.Component;

@Component
public class FabricaFactura {
    public Factura guardar(ComandoFactura comandoFactura){
        return new Factura(
                comandoFactura.getIdFactura(),
                comandoFactura.getTipoModalidad(),
                comandoFactura.getIdPropietario(),
                comandoFactura.getIdVehiculo(),
                comandoFactura.getDescripcion(),
                comandoFactura.getEstado());
    }
}
