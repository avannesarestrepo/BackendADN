package com.ceiba.factura.comando.manejador;

import com.ceiba.factura.comando.ComandoFactura;
import com.ceiba.factura.comando.fabrica.FabricaFactura;
import com.ceiba.factura.modelo.entidad.Factura;
import com.ceiba.factura.servicio.ServicioActualizarFactura;
import com.ceiba.factura.servicio.ServicioCrearFactura;
import com.ceiba.manejador.ManejadorComando;
import org.springframework.stereotype.Component;

@Component
public class ManejadorActualizarFactura implements ManejadorComando<ComandoFactura> {
    private final FabricaFactura fabricaFactura;
    private final ServicioActualizarFactura servicioActualizarFactura;

    public ManejadorActualizarFactura(FabricaFactura fabricaFactura, ServicioActualizarFactura servicioActualizarFactura) {
        this.fabricaFactura = fabricaFactura;
        this.servicioActualizarFactura = servicioActualizarFactura;
    }

    @Override
    public void ejecutar(ComandoFactura comandoFactura) {
        Factura factura = fabricaFactura.guardar(comandoFactura);
        this.servicioActualizarFactura.ejecutar(factura);
    }
}
