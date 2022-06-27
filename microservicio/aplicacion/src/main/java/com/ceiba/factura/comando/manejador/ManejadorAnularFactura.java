package com.ceiba.factura.comando.manejador;

import com.ceiba.factura.puerto.repositorio.RepositorioFactura;
import com.ceiba.factura.servicio.ServicioAnularFactura;
import com.ceiba.manejador.ManejadorComando;
import org.springframework.stereotype.Component;

@Component
public class ManejadorAnularFactura implements ManejadorComando<Long> {

    private final ServicioAnularFactura servicioAnularFactura;

    private final RepositorioFactura repositorioFactura;

    public ManejadorAnularFactura(ServicioAnularFactura servicioAnularFactura, RepositorioFactura repositorioFactura) {
        this.servicioAnularFactura = servicioAnularFactura;
        this.repositorioFactura = repositorioFactura;
    }

    @Override
    public void ejecutar(Long id) {
        servicioAnularFactura.ejecutar(repositorioFactura.obtenerFacturaPorId(id));
    }
}
