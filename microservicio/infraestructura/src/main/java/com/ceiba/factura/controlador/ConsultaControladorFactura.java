package com.ceiba.factura.controlador;

import com.ceiba.factura.consulta.ManejadorConsultaFactura;
import com.ceiba.factura.modelo.dto.FacturaDTO;
import org.springframework.web.bind.annotation.*;

@CrossOrigin(origins = "http://localhost:4200", maxAge = 3600)
@RestController
@RequestMapping("/factura")
public class ConsultaControladorFactura {

    private final ManejadorConsultaFactura manejadorConsultaFactura;

    public ConsultaControladorFactura(ManejadorConsultaFactura manejadorConsultaFactura) {
        this.manejadorConsultaFactura = manejadorConsultaFactura;
    }

    @GetMapping(params = {"id"})
    public FacturaDTO listarPorId(@RequestParam(value = "id", required = true) Long id){
        return manejadorConsultaFactura.obtenerPorId(id);
    }
}
