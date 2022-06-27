package com.ceiba.factura.comando.manejador;

import com.ceiba.ComandoRespuesta;
import com.ceiba.factura.comando.ComandoSolicitarFactura;
import com.ceiba.factura.comando.fabrica.FabricaSolicitarFactura;
import com.ceiba.factura.servicio.ServicioFacturar;
import com.ceiba.manejador.ManejadorComandoRespuesta;
import org.springframework.stereotype.Component;

@Component
public class ManejadorFacturar implements ManejadorComandoRespuesta<ComandoSolicitarFactura, ComandoRespuesta<Long>> {

    private final FabricaSolicitarFactura fabricaSolicitarFactura;
    private final ServicioFacturar servicioFacturar;

    public ManejadorFacturar(FabricaSolicitarFactura fabricaSolicitarFactura, ServicioFacturar servicioFacturar) {
        this.fabricaSolicitarFactura = fabricaSolicitarFactura;
        this.servicioFacturar = servicioFacturar;
    }


    @Override
    public ComandoRespuesta<Long> ejecutar(ComandoSolicitarFactura comandoSolicitarFactura) {
        return new ComandoRespuesta<>(servicioFacturar.
                ejecutar(fabricaSolicitarFactura.crear(comandoSolicitarFactura)));
    }
}
