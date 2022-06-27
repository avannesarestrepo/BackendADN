package com.ceiba.propietario.comando.manejador;

import com.ceiba.manejador.ManejadorComando;
import com.ceiba.propietario.servicio.ServicioEliminarPropietario;
import org.springframework.stereotype.Component;

@Component
public class ManejadorEliminarPropietario implements ManejadorComando<String> {

    private final ServicioEliminarPropietario serviciosEliminarPropietario;

    public ManejadorEliminarPropietario(ServicioEliminarPropietario serviciosEliminarPropietario) {
        this.serviciosEliminarPropietario = serviciosEliminarPropietario;
    }

    @Override
    public void ejecutar(String documento) {
        serviciosEliminarPropietario.ejecutar(documento);
    }

}
