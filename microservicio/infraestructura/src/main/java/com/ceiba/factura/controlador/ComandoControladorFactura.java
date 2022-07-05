package com.ceiba.factura.controlador;

import com.ceiba.ComandoRespuesta;
import com.ceiba.factura.comando.ComandoModificarFactura;
import com.ceiba.factura.comando.ComandoSolicitarFactura;
import com.ceiba.factura.comando.manejador.ManejadorAnularFactura;
import com.ceiba.factura.comando.manejador.ManejadorFacturar;
import com.ceiba.factura.comando.manejador.ManejadorModificarFactura;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@CrossOrigin(origins = "http://localhost:4200", maxAge = 3600)
@RestController
@RequestMapping("/factura")
@Tag(name = "Controlador comando factura")
public class ComandoControladorFactura {

    private final ManejadorFacturar manejadorFacturar;
    private final ManejadorAnularFactura manejadorAnularFactura;
    private final ManejadorModificarFactura manejadorModificarFactura;

    public ComandoControladorFactura(ManejadorFacturar manejadorFacturar, ManejadorAnularFactura manejadorAnularFactura, ManejadorModificarFactura manejadorModificarFactura) {
        this.manejadorFacturar = manejadorFacturar;
        this.manejadorAnularFactura = manejadorAnularFactura;
        this.manejadorModificarFactura = manejadorModificarFactura;
    }

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping
    @Operation(summary = "Facturar", description = "Metodo utilizado para crear facturas")
    public ComandoRespuesta<Long> facturar(@RequestBody ComandoSolicitarFactura comandoSolicitarFactura){
        return this.manejadorFacturar.ejecutar(comandoSolicitarFactura);
    }

    @PutMapping(params = {"id"})
    @Operation(summary = "Facturar", description = "Metodo utilizado para anular facturas")
    public void anular(@RequestParam(value = "id") Long id){
        this.manejadorAnularFactura.ejecutar(id);
    }

    @PutMapping("/{id}")
    @Operation(summary = "Facturar", description = "Metodo que modifica una factura")
    public void modificar(@PathVariable("id") Long id, @RequestBody ComandoModificarFactura comandoModificarFactura){
        comandoModificarFactura.setId(id);
        this.manejadorModificarFactura.ejecutar(comandoModificarFactura);
    }
}
