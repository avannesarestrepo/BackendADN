package com.ceiba.vehiculo.puerto.dao;

import com.ceiba.vehiculo.modelo.dto.VehiculoDTO;
import com.ceiba.vehiculo.modelo.entidad.Vehiculo;

public interface DaoVehiculo {
    Boolean existePorId(Long id);
    Boolean existePorPlaca(String placa);
    VehiculoDTO obtenerPorId(Long id);
    VehiculoDTO obtenerPorPlaca(String placa);
}
