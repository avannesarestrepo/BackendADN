package com.ceiba.propietario.comando.manejador;

import com.ceiba.manejador.ManejadorComando;
import com.ceiba.propietario.servicio.ServiciosEliminarPropietario;
import org.springframework.stereotype.Component;

@Component
public class ManejadorEliminarPropietario implements ManejadorComando<Long> {

    private final ServiciosEliminarPropietario serviciosEliminarPropietario;

    public ManejadorEliminarPropietario(ServiciosEliminarPropietario serviciosEliminarPropietario) {
        this.serviciosEliminarPropietario = serviciosEliminarPropietario;
    }

    @Override
    public void ejecutar(Long id) {
        serviciosEliminarPropietario.ejecutar(id);
    }
}
