package com.ceiba.propietario.consulta;

import com.ceiba.propietario.modelo.dto.PropietarioDTO;
import com.ceiba.propietario.puerto.dao.DaoPropietario;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class ManejadorConsultarPropietario {

    private final DaoPropietario daoPropietario;

    public ManejadorConsultarPropietario(DaoPropietario daoPropietario) {
        this.daoPropietario = daoPropietario;
    }

    public PropietarioDTO obtenerPorId(Long id){
        return daoPropietario.obtenerPorId(id);
    }
    public PropietarioDTO obtenerPorDocumento(String documento){
        return daoPropietario.obtenerPorDocumento(documento);
    }
    public List<PropietarioDTO> obtenerPropietarios(){
        return daoPropietario.obtenerPropietarios();
    }

}
