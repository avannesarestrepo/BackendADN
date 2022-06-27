package com.ceiba.propietario.controlador;

import com.ceiba.propietario.consulta.ManejadorConsultarPropietario;
import com.ceiba.propietario.modelo.dto.PropietarioDTO;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = "http://localhost:4200", maxAge = 3600)
@RestController
@RequestMapping("/propietario")
public class ConsultaControladorPropietario {

    private final ManejadorConsultarPropietario manejadorConsultarPropietario;

    public ConsultaControladorPropietario(ManejadorConsultarPropietario manejadorConsultarPropietario) {
        this.manejadorConsultarPropietario = manejadorConsultarPropietario;
    }

    @GetMapping(params = {"documento"})
    public PropietarioDTO listarPorDocumento(@RequestParam(value = "documento", required = true) String documento){
        return manejadorConsultarPropietario.obtenerPorDocumento(documento);
    }

    @GetMapping(params = {"id"})
    public PropietarioDTO listarPorId(@RequestParam(value = "id", required = true) Long id){
        return manejadorConsultarPropietario.obtenerPorId(id);
    }

    @GetMapping()
    public List<PropietarioDTO> listarPropietarios(){
        return manejadorConsultarPropietario.obtenerPropietarios();
    }
}
