package com.ceiba.factura.servicio;

import com.ceiba.factura.modelo.entidad.Factura;
import com.ceiba.factura.modelo.entidad.SolicitarFactura;
import com.ceiba.factura.puerto.repositorio.RepositorioFactura;

public class ServicioFacturar {

    private final RepositorioFactura repositorioFactura;

    public ServicioFacturar(RepositorioFactura repositorioFactura) {
        this.repositorioFactura = repositorioFactura;
    }

    public Long ejecutar(SolicitarFactura solicitarFactura){
        Factura factura = Factura.crear(solicitarFactura);
        return repositorioFactura.crear(factura);
    }
}
