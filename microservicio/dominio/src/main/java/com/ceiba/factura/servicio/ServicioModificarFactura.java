package com.ceiba.factura.servicio;

import com.ceiba.dominio.ValidadorArgumento;
import com.ceiba.factura.modelo.entidad.Factura;
import com.ceiba.factura.puerto.repositorio.RepositorioFactura;

public class ServicioModificarFactura {

    private final RepositorioFactura repositorioFactura;

    public ServicioModificarFactura(RepositorioFactura repositorioFactura) {
        this.repositorioFactura = repositorioFactura;
    }

    public void ejecutar(Factura factura){
        ValidadorArgumento.validarObligatorio(factura, "No existe una factura para modificar");
        repositorioFactura.actualizar(factura);
    }
}
