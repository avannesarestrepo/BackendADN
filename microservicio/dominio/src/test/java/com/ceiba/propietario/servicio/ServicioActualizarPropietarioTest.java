package com.ceiba.propietario.servicio;

import com.ceiba.BasePrueba;
import com.ceiba.dominio.excepcion.ExcepcionSinDatos;
import com.ceiba.propietario.modelo.entidad.Propietario;
import com.ceiba.propietario.modelo.entidad.PropietarioTestDataBuilder;
import com.ceiba.propietario.puerto.repositorio.RepositorioPropietario;
import com.ceiba.propietario.servicio.ServicioActualizarPropietario;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.when;

public class ServicioActualizarPropietarioTest {

    private static final String NOT_EXISTS = "El propietario no existe en el sistema";
    @Test
    void actualizarPropietarioExitosamente(){
        //arrange
        Propietario propietario = new PropietarioTestDataBuilder().conPropietarioPorDefecto().build();
        RepositorioPropietario repositorioPropietario = Mockito.mock(RepositorioPropietario.class);
        when(repositorioPropietario.existePropietario(any())).thenReturn(true);
        ServicioActualizarPropietario servicioActualizarPropietario = new ServicioActualizarPropietario(repositorioPropietario);
        //act
        servicioActualizarPropietario.ejecutar(propietario);
        //assert
        Mockito.verify(repositorioPropietario, Mockito.times(1)).actualizar(propietario);
    }

    @Test
    void actualizarPropietarioDebeFallar(){
        //arrange
        Propietario propietario = new PropietarioTestDataBuilder().conPropietarioPorDefecto().build();
        RepositorioPropietario repositorioPropietario = Mockito.mock(RepositorioPropietario.class);
        when(repositorioPropietario.existePropietario(any())).thenReturn(false);
        ServicioActualizarPropietario servicioActualizarPropietario = new ServicioActualizarPropietario(repositorioPropietario);
        //act - assert
        BasePrueba.assertThrows(() -> servicioActualizarPropietario.ejecutar(propietario), ExcepcionSinDatos.class, NOT_EXISTS);
    }
}
