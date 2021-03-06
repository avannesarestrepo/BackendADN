package com.ceiba.vehiculo.comando.manejador;

import com.ceiba.ComandoRespuesta;
import com.ceiba.manejador.ManejadorComandoRespuesta;
import com.ceiba.vehiculo.comando.ComandoVehiculo;
import com.ceiba.vehiculo.comando.fabrica.FabricaVehiculo;
import com.ceiba.vehiculo.modelo.entidad.Vehiculo;
import com.ceiba.vehiculo.servicio.ServicioCrearVehiculo;
import org.springframework.stereotype.Component;

@Component
public class ManejadorGuardarVehiculo implements ManejadorComandoRespuesta<ComandoVehiculo, ComandoRespuesta<Long>> {

    private final FabricaVehiculo fabricaVehiculo;
    private final ServicioCrearVehiculo servicioCrearVehiculo;

    public ManejadorGuardarVehiculo(FabricaVehiculo fabricaVehiculo, ServicioCrearVehiculo servicioCrearVehiculo) {
        this.fabricaVehiculo = fabricaVehiculo;
        this.servicioCrearVehiculo = servicioCrearVehiculo;
    }

    @Override
    public ComandoRespuesta<Long> ejecutar(ComandoVehiculo comandoVehiculo) {
        Vehiculo vehiculo = fabricaVehiculo.guardar(comandoVehiculo);
        return new ComandoRespuesta<>(servicioCrearVehiculo.execute(vehiculo));
    }
}
