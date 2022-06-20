package com.ceiba.propietario.puerto.repositorio;

import com.ceiba.propietario.modelo.entidad.Propietario;

public interface RepositorioPropietario {

    Long guardar(Propietario propietario);

    void actualizar(Propietario propietario);

    void eliminar(Long id);

    Boolean existeConDocumento(String documento);

    Boolean existeConId(Long id);
}
