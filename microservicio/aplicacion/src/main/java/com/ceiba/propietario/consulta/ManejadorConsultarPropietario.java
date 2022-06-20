package com.ceiba.propietario.consulta;

import com.ceiba.propietario.modelo.dto.PropietarioDTO;
import com.ceiba.propietario.puerto.dao.DaoPropietario;
import org.springframework.stereotype.Component;

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

    public Boolean existePorId(Long id){
        return daoPropietario.existePorId(id);
    }

    public Boolean existePorDocumento(String documento){
        return daoPropietario.existePorDocumento(documento);
    }
}
