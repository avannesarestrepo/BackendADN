package com.ceiba.factura.comando.manejador;

import com.ceiba.factura.comando.ComandoModificarFactura;
import com.ceiba.factura.comando.fabrica.FabricaSolicitarFactura;
import com.ceiba.factura.modelo.entidad.Factura;
import com.ceiba.factura.servicio.ServicioModificarFactura;
import com.ceiba.manejador.ManejadorComando;
import org.springframework.stereotype.Component;

@Component
public class ManejadorModificarFactura implements ManejadorComando<ComandoModificarFactura> {

    private final ServicioModificarFactura servicioModificarFactura;

    private final FabricaSolicitarFactura fabricaSolicitarFactura;

    public ManejadorModificarFactura(ServicioModificarFactura servicioModificarFactura, FabricaSolicitarFactura fabricaSolicitarFactura) {
        this.servicioModificarFactura = servicioModificarFactura;
        this.fabricaSolicitarFactura = fabricaSolicitarFactura;
    }

    @Override
    public void ejecutar(ComandoModificarFactura comandoModificarFactura) {
        Factura factura = fabricaSolicitarFactura.modificar(comandoModificarFactura);
        servicioModificarFactura.ejecutar(factura);
    }
}
