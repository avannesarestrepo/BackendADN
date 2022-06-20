package com.ceiba.vehiculo.consulta;

import com.ceiba.vehiculo.modelo.dto.VehiculoDTO;
import com.ceiba.vehiculo.puerto.dao.DaoVehiculo;
import org.springframework.stereotype.Component;

@Component
public class ManejadorConsultarVehiculo {

    private DaoVehiculo daoVehiculo;

    public ManejadorConsultarVehiculo(DaoVehiculo daoVehiculo) {
        this.daoVehiculo = daoVehiculo;
    }

    public VehiculoDTO obtenerPorId(Long id){
        return daoVehiculo.obtenerPorId(id);
    }

    public VehiculoDTO obtenerPorPlaca(String placa){
        return daoVehiculo.obtenerPorPlaca(placa);
    }

    public Boolean existePorId(Long id){
        return daoVehiculo.existePorId(id);
    }

    public Boolean existePorPlaca(String placa){
        return daoVehiculo.existePorPlaca(placa);
    }
}
