package com.ceiba.vehiculo.controlador;

import com.ceiba.vehiculo.consulta.ManejadorConsultaVehiculo;
import com.ceiba.vehiculo.modelo.dto.VehiculoDTO;
import org.springframework.web.bind.annotation.*;

@CrossOrigin(origins = "http://localhost:4200", maxAge = 3600)
@RestController
@RequestMapping("/vehiculo")
public class ConsultaControladorVehiculo {

    private final ManejadorConsultaVehiculo manejadorConsultaVehiculo;

    public ConsultaControladorVehiculo(ManejadorConsultaVehiculo manejadorConsultaVehiculo) {
        this.manejadorConsultaVehiculo = manejadorConsultaVehiculo;
    }

    @GetMapping(params = {"placa"})
    public VehiculoDTO obtenerVehiculoPorPlaca(@RequestParam(value = "placa") String placa){
        return manejadorConsultaVehiculo.obtenerVehiculoPorPlaca(placa);
    }

    @GetMapping("/{id}")
    public VehiculoDTO obtenerVehiculoPorId(@PathVariable("id") Long id){
        return manejadorConsultaVehiculo.obtenerVehiculoPorId(id);
    }

}
