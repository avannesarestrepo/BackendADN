package com.ceiba.propietario.puerto.dao;

import com.ceiba.propietario.modelo.dto.PropietarioDTO;

import java.util.List;

public interface DaoPropietario {
    PropietarioDTO obtenerPorId(Long id);
    PropietarioDTO obtenerPorDocumento(String documento);
    List<PropietarioDTO> obtenerPropietarios();

}
