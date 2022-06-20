package com.ceiba.propietario.comando.fabrica;

import com.ceiba.propietario.comando.ComandoPropietario;
import com.ceiba.propietario.modelo.entidad.Propietario;
import org.springframework.stereotype.Component;

@Component
public class FabricaPropietario {
    public Propietario guardar(ComandoPropietario comandoPropietario){
        return new Propietario(
                comandoPropietario.getId(),
                comandoPropietario.getTipoDocumento(),
                comandoPropietario.getDocumento(),
                comandoPropietario.getNombreCompleto(),
                comandoPropietario.getEmail(),
                comandoPropietario.getTelefono()
        );
    }
}
