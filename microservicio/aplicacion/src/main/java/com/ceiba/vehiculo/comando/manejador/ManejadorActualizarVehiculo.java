package com.ceiba.vehiculo.comando.manejador;

import com.ceiba.manejador.ManejadorComando;
import com.ceiba.vehiculo.comando.ComandoVehiculo;
import com.ceiba.vehiculo.comando.fabrica.FabricaVehiculo;
import com.ceiba.vehiculo.modelo.entidad.Vehiculo;
import com.ceiba.vehiculo.servicio.ServicioActualizarVehiculo;
import org.springframework.stereotype.Component;

@Component
public class ManejadorActualizarVehiculo implements ManejadorComando<ComandoVehiculo> {

    private final FabricaVehiculo fabricaVehiculo;
    private final ServicioActualizarVehiculo servicioActualizarVehiculo;

    public ManejadorActualizarVehiculo(FabricaVehiculo fabricaVehiculo, ServicioActualizarVehiculo servicioActualizarVehiculo) {
        this.fabricaVehiculo = fabricaVehiculo;
        this.servicioActualizarVehiculo = servicioActualizarVehiculo;
    }

    @Override
    public void ejecutar(ComandoVehiculo comandoVehiculo) {
        Vehiculo vehiculo = fabricaVehiculo.crear(comandoVehiculo);
        this.servicioActualizarVehiculo.ejecutar(vehiculo);
    }
}
