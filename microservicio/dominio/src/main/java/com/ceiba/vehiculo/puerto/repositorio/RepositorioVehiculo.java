package com.ceiba.vehiculo.puerto.repositorio;

import com.ceiba.vehiculo.modelo.entidad.Vehiculo;

public interface RepositorioVehiculo {
    Long crear(Vehiculo vehiculo);
    void actualizar(Vehiculo vehiculo);
    void eliminar(String placa);
    Boolean existeVehiculo(Vehiculo vehiculo);
    Vehiculo obtenerVehiculo(Vehiculo vehiculo);
    Vehiculo obtenerVehiculoPorId(Long id);
}
