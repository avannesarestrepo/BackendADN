package com.ceiba.vehiculo.servicio;

import com.ceiba.vehiculo.puerto.repositorio.RepositorioVehiculo;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

public class ServicioEliminarVehiculoTest {

    @Test
    void eliminarVehiculoExitosamente(){
        //arrange
        RepositorioVehiculo repositorioVehiculo = Mockito.mock(RepositorioVehiculo.class);
        ServicioEliminarVehiculo servicioEliminarVehiculo = new ServicioEliminarVehiculo(repositorioVehiculo);
        //act
        servicioEliminarVehiculo.ejecutar("RTE024");
        //asserts
        Mockito.verify(repositorioVehiculo, Mockito.times(1)).eliminar("RTE024");
    }
}
