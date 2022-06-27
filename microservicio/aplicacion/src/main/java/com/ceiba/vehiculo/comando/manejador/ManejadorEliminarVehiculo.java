package com.ceiba.vehiculo.comando.manejador;

import com.ceiba.manejador.ManejadorComando;
import com.ceiba.vehiculo.servicio.ServicioEliminarVehiculo;
import org.springframework.stereotype.Component;

@Component
public class ManejadorEliminarVehiculo implements ManejadorComando<String> {

    private final ServicioEliminarVehiculo servicioEliminarVehiculo;

    public ManejadorEliminarVehiculo(ServicioEliminarVehiculo servicioEliminarVehiculo) {
        this.servicioEliminarVehiculo = servicioEliminarVehiculo;
    }

    @Override
    public void ejecutar(String placa) {
        servicioEliminarVehiculo.ejecutar(placa);
    }
}
