package com.ceiba.propietario.comando.manejador;

import com.ceiba.propietario.comando.ComandoPropietario;
import com.ceiba.propietario.comando.fabrica.FabricaPropietario;
import com.ceiba.manejador.ManejadorComando;
import com.ceiba.propietario.modelo.entidad.Propietario;
import com.ceiba.propietario.servicio.ServicioActualizarPropietario;
import org.springframework.stereotype.Component;

@Component
public class ManejadorActualizarPropietario implements ManejadorComando<ComandoPropietario> {

    private final FabricaPropietario fabricaPropietario;
    private final ServicioActualizarPropietario servicioActualizarPropietario;

    public ManejadorActualizarPropietario(FabricaPropietario fabricaPropietario, ServicioActualizarPropietario servicioActualizarPropietario) {
        this.fabricaPropietario = fabricaPropietario;
        this.servicioActualizarPropietario = servicioActualizarPropietario;
    }

    @Override
    public void ejecutar(ComandoPropietario comando) {
        Propietario propietario = fabricaPropietario.guardar(comando);
        this.servicioActualizarPropietario.ejecutar(propietario);
    }
}
