package com.ceiba.vehiculo.puerto.dao;

import com.ceiba.vehiculo.modelo.dto.VehiculoDTO;

public interface DaoVehiculo {
    VehiculoDTO obtenerVehiculoPorId(Long id);
    VehiculoDTO obtenerVehiculoPorPlaca(String placa);
}
