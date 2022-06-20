package com.ceiba.factura.comando.manejador;

import com.ceiba.ComandoRespuesta;
import com.ceiba.factura.comando.ComandoFactura;
import com.ceiba.factura.comando.fabrica.FabricaFactura;
import com.ceiba.factura.modelo.entidad.Factura;
import com.ceiba.factura.servicio.ServicioCrearFactura;
import com.ceiba.manejador.ManejadorComandoRespuesta;
import org.springframework.stereotype.Component;

@Component
public class ManejadorGuardarFactura implements ManejadorComandoRespuesta<ComandoFactura, ComandoRespuesta<Long>> {

    private final FabricaFactura fabricaFactura;
    private final ServicioCrearFactura servicioCrearFactura;

    public ManejadorGuardarFactura(FabricaFactura fabricaFactura, ServicioCrearFactura servicioCrearFactura) {
        this.fabricaFactura = fabricaFactura;
        this.servicioCrearFactura = servicioCrearFactura;
    }

    @Override
    public ComandoRespuesta<Long> ejecutar(ComandoFactura comandoFactura) {
        Factura factura = fabricaFactura.guardar(comandoFactura);
        return new ComandoRespuesta<>(servicioCrearFactura.ejecutar(factura));
    }
}
