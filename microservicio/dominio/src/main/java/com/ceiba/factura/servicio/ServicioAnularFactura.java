package com.ceiba.factura.servicio;

import com.ceiba.dominio.ValidadorArgumento;
import com.ceiba.factura.modelo.entidad.Factura;
import com.ceiba.factura.puerto.repositorio.RepositorioFactura;

public class ServicioAnularFactura {

    private final RepositorioFactura repositorioFactura;

    public ServicioAnularFactura(RepositorioFactura repositorioFactura) {
        this.repositorioFactura = repositorioFactura;
    }

    public void ejecutar(Factura factura){
        ValidadorArgumento.validarObligatorio(factura, "No existe una factura para anular");
        repositorioFactura.anular(factura.getId());
    }
}
