package com.ceiba.propietario.comando.manejador;

import com.ceiba.ComandoRespuesta;
import com.ceiba.propietario.comando.ComandoPropietario;
import com.ceiba.propietario.comando.fabrica.FabricaPropietario;
import com.ceiba.manejador.ManejadorComandoRespuesta;
import com.ceiba.propietario.modelo.entidad.Propietario;
import com.ceiba.propietario.servicio.ServicioCrearPropietario;
import org.springframework.stereotype.Component;

@Component
public class ManejadorGuardarPropietario implements ManejadorComandoRespuesta<ComandoPropietario, ComandoRespuesta<Long>> {

    private final FabricaPropietario fabricaPropietario;

    private final ServicioCrearPropietario servicioCrearPropietario;

    public ManejadorGuardarPropietario(FabricaPropietario fabricaPropietario, ServicioCrearPropietario servicioCrearPropietario) {
        this.fabricaPropietario = fabricaPropietario;
        this.servicioCrearPropietario = servicioCrearPropietario;
    }

    @Override
    public ComandoRespuesta<Long> ejecutar(ComandoPropietario comandoPropietario) {
        Propietario propietario = fabricaPropietario.guardar(comandoPropietario);
        return new ComandoRespuesta<>(servicioCrearPropietario.ejecutar(propietario));
    }
}
