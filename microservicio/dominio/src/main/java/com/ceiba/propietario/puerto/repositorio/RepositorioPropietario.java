package com.ceiba.propietario.puerto.repositorio;

import com.ceiba.propietario.modelo.entidad.Propietario;

public interface RepositorioPropietario {
    Long crear(Propietario propietario);
    void actualizar(Propietario propietario);
    void eliminar(String documento);
    Boolean existePropietario(Propietario propietario);
    Propietario obtenerPropietario(Propietario propietario);
    Propietario obtenerPorId(Long id);
}
