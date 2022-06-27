package com.ceiba.factura.puerto.repositorio;

import com.ceiba.factura.modelo.entidad.Factura;

public interface RepositorioFactura {
    Long crear(Factura factura);
    void actualizar(Factura factura);
    void anular(Long id);
    Factura obtenerFacturaPorId(Long id);
}
