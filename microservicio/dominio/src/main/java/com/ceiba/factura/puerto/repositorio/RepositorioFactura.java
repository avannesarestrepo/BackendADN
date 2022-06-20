package com.ceiba.factura.puerto.repositorio;

import com.ceiba.factura.modelo.entidad.Factura;

public interface RepositorioFactura {
    Long guardar(Factura factura);

    void actualizar(Factura factura);

    void eliminar(Long id);

    Boolean existeConId(Long id);
}
