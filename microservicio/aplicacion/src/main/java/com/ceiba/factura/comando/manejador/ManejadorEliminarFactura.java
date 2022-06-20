package com.ceiba.factura.comando.manejador;

import com.ceiba.factura.servicio.ServicioEliminarFactura;
import com.ceiba.manejador.ManejadorComando;
import org.springframework.stereotype.Component;

@Component
public class ManejadorEliminarFactura implements ManejadorComando<Long> {
    private final ServicioEliminarFactura servicioEliminarFactura;

    public ManejadorEliminarFactura(ServicioEliminarFactura servicioEliminarFactura) {
        this.servicioEliminarFactura = servicioEliminarFactura;
    }

    @Override
    public void ejecutar(Long id) {
        servicioEliminarFactura.ejecutar(id);
    }
}
