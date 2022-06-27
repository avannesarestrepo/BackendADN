package com.ceiba.factura.puerto.dao;

import com.ceiba.factura.modelo.dto.FacturaDTO;

import java.util.List;

public interface DaoFactura {

    List<FacturaDTO> obtenerFacturas();
    FacturaDTO obtenerFacturaPorId(Long id);
    List<FacturaDTO> obtenerFacturasPorVehiculo(String placa);
}
