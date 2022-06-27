package com.ceiba.vehiculo.consulta;

import com.ceiba.vehiculo.modelo.dto.VehiculoDTO;
import com.ceiba.vehiculo.puerto.dao.DaoVehiculo;
import org.springframework.stereotype.Component;

@Component
public class ManejadorConsultaVehiculo {

    private final DaoVehiculo daoVehiculo;

    public ManejadorConsultaVehiculo(DaoVehiculo daoVehiculo) {
        this.daoVehiculo = daoVehiculo;
    }

    public VehiculoDTO obtenerVehiculoPorPlaca(String placa){
        return daoVehiculo.obtenerVehiculoPorPlaca(placa);
    }

    public VehiculoDTO obtenerVehiculoPorId(Long id){
        return daoVehiculo.obtenerVehiculoPorId(id);
    }
}
