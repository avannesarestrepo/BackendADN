package com.ceiba.propietario.controlador;

import com.ceiba.ComandoRespuesta;
import com.ceiba.propietario.comando.ComandoPropietario;
import com.ceiba.propietario.comando.manejador.ManejadorActualizarPropietario;
import com.ceiba.propietario.comando.manejador.ManejadorEliminarPropietario;
import com.ceiba.propietario.comando.manejador.ManejadorCrearPropietario;
import org.springframework.web.bind.annotation.*;

@CrossOrigin(origins = "http://localhost:4200", maxAge = 3600)
@RestController
@RequestMapping("/propietario")
public class ComandoControladorPropietario {

    private final ManejadorCrearPropietario manejadorGuardarPropietario;

    private final ManejadorActualizarPropietario manejadorActualizarPropietario;

    private final ManejadorEliminarPropietario manejadorEliminarPropietario;

    public ComandoControladorPropietario(ManejadorCrearPropietario manejadorGuardarPropietario, ManejadorActualizarPropietario manejadorActualizarPropietario, ManejadorEliminarPropietario manejadorEliminarPropietario) {
        this.manejadorGuardarPropietario = manejadorGuardarPropietario;
        this.manejadorActualizarPropietario = manejadorActualizarPropietario;
        this.manejadorEliminarPropietario = manejadorEliminarPropietario;
    }

    @PostMapping
    public ComandoRespuesta<Long> crearPropietario(@RequestBody ComandoPropietario comandoPropietario){
        return manejadorGuardarPropietario.ejecutar(comandoPropietario);
    }

    @PutMapping("/{id}")
    public void actualizarPropietario(@PathVariable("id") Long id, @RequestBody ComandoPropietario comandoPropietario){
        comandoPropietario.setId(id);
        manejadorActualizarPropietario.ejecutar(comandoPropietario);
    }

    @DeleteMapping(params = {"documento"})
    public void eliminarPropietario(@RequestParam(value = "documento", required = true) String documento){
        manejadorEliminarPropietario.ejecutar(documento);
    }
}
