package com.ceiba.vehiculo.controlador;

import com.ceiba.ComandoRespuesta;
import com.ceiba.vehiculo.comando.ComandoVehiculo;
import com.ceiba.vehiculo.comando.manejador.ManejadorActualizarVehiculo;
import com.ceiba.vehiculo.comando.manejador.ManejadorEliminarVehiculo;
import com.ceiba.vehiculo.comando.manejador.ManejadorGuardarVehiculo;
import org.springframework.web.bind.annotation.*;

@CrossOrigin(origins = "http://localhost:4200", maxAge = 3600)
@RestController
@RequestMapping("/vehiculo")
public class ComandoControladorVehiculo {

    private final ManejadorGuardarVehiculo manejadorGuardarVehiculo;

    private final ManejadorActualizarVehiculo manejadorActualizarVehiculo;

    private final ManejadorEliminarVehiculo manejadorEliminarVehiculo;

    public ComandoControladorVehiculo(ManejadorGuardarVehiculo manejadorGuardarVehiculo, ManejadorActualizarVehiculo manejadorActualizarVehiculo, ManejadorEliminarVehiculo manejadorEliminarVehiculo) {
        this.manejadorGuardarVehiculo = manejadorGuardarVehiculo;
        this.manejadorActualizarVehiculo = manejadorActualizarVehiculo;
        this.manejadorEliminarVehiculo = manejadorEliminarVehiculo;
    }

    @PostMapping
    public ComandoRespuesta<Long> guardarVehiculo(@RequestBody ComandoVehiculo comandoVehiculo){
        return manejadorGuardarVehiculo.ejecutar(comandoVehiculo);
    }

    @PutMapping("/{id}")
    public void actualizarVehiculo(@PathVariable("id") Long id, @RequestBody ComandoVehiculo comandoVehiculo){
        comandoVehiculo.setIdVehiculo(id);
        manejadorActualizarVehiculo.ejecutar(comandoVehiculo);
    }

    @DeleteMapping("/{id}")
    public void eliminarVehiculo(@PathVariable("id") Long id){
        manejadorEliminarVehiculo.ejecutar(id);
    }
}
