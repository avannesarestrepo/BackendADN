package com.ceiba.vehiculo.controlador;

import com.ceiba.vehiculo.consulta.ManejadorConsultarVehiculo;
import com.ceiba.vehiculo.modelo.dto.VehiculoDTO;
import org.springframework.web.bind.annotation.*;

@CrossOrigin(origins = "http://localhost:4200", maxAge = 3600)
@RestController
@RequestMapping("/vehiculo")
public class ConsultaControladorVehiculo {

    private final ManejadorConsultarVehiculo manejadorConsultarVehiculo;

    public ConsultaControladorVehiculo(ManejadorConsultarVehiculo manejadorConsultarVehiculo) {
        this.manejadorConsultarVehiculo = manejadorConsultarVehiculo;
    }

    @GetMapping(params = {"id"})
    public VehiculoDTO listarPorId(@RequestParam(value = "id", required = true) Long id){
        return manejadorConsultarVehiculo.obtenerPorId(id);
    }

    @GetMapping(params = {"placa"})
    public VehiculoDTO listarPorPlaca(@RequestParam(value = "placa", required = true) String placa){
        return manejadorConsultarVehiculo.obtenerPorPlaca(placa);
    }

    @GetMapping("/{id}")
    public Boolean existePorId(@PathVariable("id") Long id){
        return manejadorConsultarVehiculo.existePorId(id);
    }

    @GetMapping(params = {"validarPlaca"})
    public Boolean existePorPlaca(@RequestParam("validarPlaca") String placa){
        return manejadorConsultarVehiculo.existePorPlaca(placa);
    }
}
