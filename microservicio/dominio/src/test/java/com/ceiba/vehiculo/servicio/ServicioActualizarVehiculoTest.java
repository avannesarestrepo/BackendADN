package com.ceiba.vehiculo.servicio;

import com.ceiba.BasePrueba;
import com.ceiba.dominio.excepcion.ExcepcionSinDatos;
import com.ceiba.vehiculo.modelo.entidad.Vehiculo;
import com.ceiba.vehiculo.modelo.entidad.VehiculoTestDataBuilder;
import com.ceiba.vehiculo.puerto.repositorio.RepositorioVehiculo;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.Mockito;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

public class ServicioActualizarVehiculoTest {

    private static final String VEHICULO_NO_EXISTE = "El vehiculo no existe en el sistema";

    @Test
    void actualizarVehiculoExitosamente(){
        //arrange
        RepositorioVehiculo repositorioVehiculo = Mockito.mock(RepositorioVehiculo.class);
        Vehiculo vehiculo = new VehiculoTestDataBuilder().conVehiculoPorDefecto().build();
        when(repositorioVehiculo.existeVehiculo(any())).thenReturn(true);
        ServicioActualizarVehiculo servicioActualizarVehiculo = new ServicioActualizarVehiculo(repositorioVehiculo);
        //act
        servicioActualizarVehiculo.ejecutar(vehiculo);
        //assert
        Mockito.verify(repositorioVehiculo, Mockito.times(1)).actualizar(vehiculo);
    }

    @Test
    void actualizarVehiculoNoExistenteDebeFallar(){
        //arrange
        RepositorioVehiculo repositorioVehiculo = Mockito.mock(RepositorioVehiculo.class);
        Vehiculo vehiculo = new VehiculoTestDataBuilder().conVehiculoPorDefecto().build();
        when(repositorioVehiculo.existeVehiculo(any())).thenReturn(false);

        ServicioActualizarVehiculo servicioActualizarVehiculo = new ServicioActualizarVehiculo(repositorioVehiculo);
        //act - asserts
        BasePrueba.assertThrows(() -> servicioActualizarVehiculo.ejecutar(vehiculo),
                ExcepcionSinDatos.class, VEHICULO_NO_EXISTE);

    }
}
