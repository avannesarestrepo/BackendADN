package com.ceiba.factura.puerto.dao;

import com.ceiba.factura.modelo.dto.FacturaDTO;

public interface DaoFactura {
    FacturaDTO obtenerPorId(Long id);
}
