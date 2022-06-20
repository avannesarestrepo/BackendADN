package com.ceiba.vehiculo.puerto.repositorio;

import com.ceiba.vehiculo.modelo.entidad.Vehiculo;

public interface RepositorioVehiculo {
    Long guardar(Vehiculo vehiculo);
    void actualizar(Vehiculo vehiculo);
    void eliminar(Long id);
    Boolean existePorId(Long id);
    Boolean existePorPlaca(String placa);
}
