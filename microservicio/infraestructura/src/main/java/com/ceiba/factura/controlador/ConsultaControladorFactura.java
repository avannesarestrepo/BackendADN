package com.ceiba.factura.controlador;

import com.ceiba.factura.consulta.ManejadorConsultaFacturas;
import com.ceiba.factura.modelo.dto.FacturaDTO;
import io.swagger.v3.oas.annotations.Operation;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = "http://localhost:4200", maxAge = 3600)
@RestController
@RequestMapping("/factura")
public class ConsultaControladorFactura {

    private final ManejadorConsultaFacturas manejadorConsultaFacturas;

    public ConsultaControladorFactura(ManejadorConsultaFacturas manejadorConsultaFacturas) {
        this.manejadorConsultaFacturas = manejadorConsultaFacturas;
    }

    @GetMapping()
    @Operation(summary = "Facturar", description = "Metodo que consulta las facturas")
    public List<FacturaDTO> obtenerFacturas(){
        return manejadorConsultaFacturas.obtenerFacturas();
    }

    @GetMapping("/{id}")
    @Operation(summary = "Facturar", description = "Metodo que consulta las facturas por id")
    public FacturaDTO obtenerFactura(@PathVariable("id") Long id){
        return manejadorConsultaFacturas.obtenerFacturaPorId(id);
    }

    @GetMapping(params = {"placa"})
    @Operation(summary = "Facturar", description = "Metodo que consulta las facturas por plac")
    public List<FacturaDTO> obtenerFacturaPorPlaca(@RequestParam(value = "placa") String placa){
        return manejadorConsultaFacturas.obtenerFacturasPorVehiculo(placa);
    }
}
