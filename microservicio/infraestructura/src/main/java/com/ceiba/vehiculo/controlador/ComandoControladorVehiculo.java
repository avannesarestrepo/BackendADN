package com.ceiba.vehiculo.controlador;

import com.ceiba.ComandoRespuesta;
import com.ceiba.vehiculo.comando.ComandoVehiculo;
import com.ceiba.vehiculo.comando.manejador.ManejadorActualizarVehiculo;
import com.ceiba.vehiculo.comando.manejador.ManejadorCrearVehiculo;
import com.ceiba.vehiculo.comando.manejador.ManejadorEliminarVehiculo;
import org.springframework.web.bind.annotation.*;

@CrossOrigin(origins = "http://localhost:4200", maxAge = 3600)
@RestController
@RequestMapping("/vehiculo")
public class ComandoControladorVehiculo {

    private final ManejadorCrearVehiculo manejadorCrearVehiculo;
    private final ManejadorActualizarVehiculo manejadorActualizarVehiculo;
    private final ManejadorEliminarVehiculo manejadorEliminarVehiculo;

    public ComandoControladorVehiculo(ManejadorCrearVehiculo manejadorCrearVehiculo, ManejadorActualizarVehiculo manejadorActualizarVehiculo, ManejadorEliminarVehiculo manejadorEliminarVehiculo) {
        this.manejadorCrearVehiculo = manejadorCrearVehiculo;
        this.manejadorActualizarVehiculo = manejadorActualizarVehiculo;
        this.manejadorEliminarVehiculo = manejadorEliminarVehiculo;
    }

    @PostMapping
    public ComandoRespuesta<Long> crearVehiculo(@RequestBody ComandoVehiculo comandoVehiculo){
        return manejadorCrearVehiculo.ejecutar(comandoVehiculo);
    }
    @PutMapping("/{id}")
    public void actualizarVehiculo(@PathVariable("id") Long id, @RequestBody ComandoVehiculo comandoVehiculo){
        comandoVehiculo.setId(id);
        manejadorActualizarVehiculo.ejecutar(comandoVehiculo);
    }

    @DeleteMapping(params = {"placa"})
    public void eliminarVehiculo(@RequestParam(value = "placa", required = true) String placa){
        manejadorEliminarVehiculo.ejecutar(placa);
    }
}
