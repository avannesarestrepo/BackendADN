package com.ceiba.vehiculo.servicio;

import com.ceiba.BasePrueba;
import com.ceiba.dominio.excepcion.ExcepcionDuplicidad;
import com.ceiba.vehiculo.modelo.entidad.Vehiculo;
import com.ceiba.vehiculo.modelo.entidad.VehiculoTestDataBuilder;
import com.ceiba.vehiculo.puerto.repositorio.RepositorioVehiculo;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

public class ServicioCrearVehiculoTest {

    private static final String VEHICULO_EXISTE = "El Vehiculo existe en el sistema";

    @Test
    void crearVehiculoExitosamente(){
        RepositorioVehiculo repositorioVehiculo = Mockito.mock(RepositorioVehiculo.class);
        when(repositorioVehiculo.crear(any())).thenReturn(1L);
        Vehiculo vehiculo = new VehiculoTestDataBuilder().conVehiculoPorDefecto().build();
        ServicioCrearVehiculo servicioCrearVehiculo = new ServicioCrearVehiculo(repositorioVehiculo);

        Long id = servicioCrearVehiculo.ejecutar(vehiculo);

        Assertions.assertEquals(1L, id);
    }

    @Test
    void crearVehiculoDuplicadoDebeFallar(){
        RepositorioVehiculo repositorioVehiculo = Mockito.mock(RepositorioVehiculo.class);
        Vehiculo vehiculo = new VehiculoTestDataBuilder().conVehiculoPorDefecto().build();
        when(repositorioVehiculo.existeVehiculo(any())).thenReturn(true);

        ServicioCrearVehiculo servicioCrearVehiculo = new ServicioCrearVehiculo(repositorioVehiculo);

        BasePrueba.assertThrows(() -> servicioCrearVehiculo.ejecutar(vehiculo),
                ExcepcionDuplicidad.class, VEHICULO_EXISTE);
    }
}
