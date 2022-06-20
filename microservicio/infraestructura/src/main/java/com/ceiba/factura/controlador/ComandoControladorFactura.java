package com.ceiba.factura.controlador;

import com.ceiba.ComandoRespuesta;
import com.ceiba.factura.comando.ComandoFactura;
import com.ceiba.factura.comando.manejador.ManejadorActualizarFactura;
import com.ceiba.factura.comando.manejador.ManejadorEliminarFactura;
import com.ceiba.factura.comando.manejador.ManejadorGuardarFactura;
import org.springframework.web.bind.annotation.*;

@CrossOrigin(origins = "http://localhost:4200", maxAge = 3600)
@RestController
@RequestMapping("/factura")
public class ComandoControladorFactura {

    private final ManejadorGuardarFactura manejadorGuardarFactura;

    private final ManejadorActualizarFactura manejadorActualizarFactura;

    private final ManejadorEliminarFactura manejadorEliminarFactura;

    public ComandoControladorFactura(ManejadorGuardarFactura manejadorGuardarFactura, ManejadorActualizarFactura manejadorActualizarFactura, ManejadorEliminarFactura manejadorEliminarFactura) {
        this.manejadorGuardarFactura = manejadorGuardarFactura;
        this.manejadorActualizarFactura = manejadorActualizarFactura;
        this.manejadorEliminarFactura = manejadorEliminarFactura;
    }

    @PostMapping
    public ComandoRespuesta<Long> guardarFactura(@RequestBody ComandoFactura comandoFactura){
        return manejadorGuardarFactura.ejecutar(comandoFactura);
    }

    @PutMapping("/{id}")
    public void actualizarFactura(@PathVariable("id") Long id, @RequestBody ComandoFactura comandoFactura){
        comandoFactura.setIdFactura(id);
        manejadorActualizarFactura.ejecutar(comandoFactura);
    }

    @DeleteMapping("/{id}")
    public void eliminarFactura(@PathVariable("id") Long id){
        manejadorEliminarFactura.ejecutar(id);
    }
}
