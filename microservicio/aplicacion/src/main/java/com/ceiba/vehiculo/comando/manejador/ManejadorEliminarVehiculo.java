package com.ceiba.vehiculo.comando.manejador;

import com.ceiba.manejador.ManejadorComando;
import com.ceiba.vehiculo.servicio.ServicioEliminarVehiculo;
import org.springframework.stereotype.Component;

@Component
public class ManejadorEliminarVehiculo implements ManejadorComando<Long> {

    private final ServicioEliminarVehiculo servicioEliminarVehiculo;

    public ManejadorEliminarVehiculo(ServicioEliminarVehiculo servicioEliminarVehiculo) {
        this.servicioEliminarVehiculo = servicioEliminarVehiculo;
    }

    @Override
    public void ejecutar(Long id) {
        servicioEliminarVehiculo.ejecutar(id);
    }
}
