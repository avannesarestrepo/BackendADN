package com.ceiba.propietario.modelo.entidad;

import com.ceiba.BasePrueba;
import com.ceiba.dominio.excepcion.ExcepcionSinDatos;
import com.ceiba.propietario.puerto.repositorio.RepositorioPropietario;
import com.ceiba.propietario.servicio.ServicioActualizarPropietario;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.Mockito;

import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.when;

public class ServicioActualizarPropietarioTest {

    private static final String NOT_EXISTS = "El propietario no existe en el sistema";
    @Test
    void deberiaActualizarPropietarioExitosamente(){
        //arrange
        Propietario propietario = new PropietarioTestDataBuilder().conPropietarioPorDefecto().reconstruir();
        RepositorioPropietario repositorioPropietario = Mockito.mock(RepositorioPropietario.class);
        when(repositorioPropietario.existeConId(anyLong())).thenReturn(true);
        ServicioActualizarPropietario servicioActualizarPropietario = new ServicioActualizarPropietario(repositorioPropietario);
        //act
        servicioActualizarPropietario.ejecutar(propietario);
        //assert
        Mockito.verify(repositorioPropietario, Mockito.times(1)).actualizar(propietario);
    }

    @Test
    void crearRepositorioDebeFallar(){
        //arrange
        Propietario propietario = new PropietarioTestDataBuilder().conPropietarioPorDefecto().reconstruir();
        RepositorioPropietario repositorioPropietario = Mockito.mock(RepositorioPropietario.class);
        when(repositorioPropietario.existeConId(anyLong())).thenReturn(false);
        ServicioActualizarPropietario servicioActualizarPropietario = new ServicioActualizarPropietario(repositorioPropietario);
        //act - assert
        BasePrueba.assertThrows(() -> servicioActualizarPropietario.ejecutar(propietario), ExcepcionSinDatos.class, NOT_EXISTS);
    }
}
