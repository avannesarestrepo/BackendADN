package com.ceiba.propietario.puerto.dao;

import com.ceiba.propietario.modelo.dto.PropietarioDTO;

public interface DaoPropietario {
    Boolean existePorDocumento(String documento);
    Boolean existePorId(Long id);
    PropietarioDTO obtenerPorId(Long id);
    PropietarioDTO obtenerPorDocumento(String documento);

}
