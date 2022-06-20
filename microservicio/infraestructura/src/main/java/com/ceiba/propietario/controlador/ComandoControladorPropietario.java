package com.ceiba.propietario.controlador;

import com.ceiba.ComandoRespuesta;
import com.ceiba.propietario.comando.ComandoPropietario;
import com.ceiba.propietario.comando.manejador.ManejadorActualizarPropietario;
import com.ceiba.propietario.comando.manejador.ManejadorEliminarPropietario;
import com.ceiba.propietario.comando.manejador.ManejadorGuardarPropietario;
import org.springframework.web.bind.annotation.*;

@CrossOrigin(origins = "http://localhost:4200", maxAge = 3600)
@RestController
@RequestMapping("/propietario")
public class ComandoControladorPropietario {

    private final ManejadorGuardarPropietario manejadorGuardarPropietario;

    private final ManejadorActualizarPropietario manejadorActualizarPropietario;

    private final ManejadorEliminarPropietario manejadorEliminarPropietario;

    public ComandoControladorPropietario(ManejadorGuardarPropietario manejadorGuardarPropietario, ManejadorActualizarPropietario manejadorActualizarPropietario, ManejadorEliminarPropietario manejadorEliminarPropietario) {
        this.manejadorGuardarPropietario = manejadorGuardarPropietario;
        this.manejadorActualizarPropietario = manejadorActualizarPropietario;
        this.manejadorEliminarPropietario = manejadorEliminarPropietario;
    }

    @PostMapping
    public ComandoRespuesta<Long> guardarPropietario(@RequestBody ComandoPropietario comandoPropietario){
        return manejadorGuardarPropietario.ejecutar(comandoPropietario);
    }

    @PutMapping("/{id}")
    public void actualizarPropietario(@PathVariable("id") Long id, @RequestBody ComandoPropietario comandoPropietario){
        comandoPropietario.setId(id);
        manejadorActualizarPropietario.ejecutar(comandoPropietario);
    }

    @DeleteMapping("/{id}")
    public void eliminarPropietario(@PathVariable("id") Long id){
        manejadorEliminarPropietario.ejecutar(id);
    }
}
