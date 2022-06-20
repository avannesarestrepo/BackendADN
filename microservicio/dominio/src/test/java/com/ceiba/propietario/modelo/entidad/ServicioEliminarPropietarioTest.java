package com.ceiba.propietario.modelo.entidad;

import com.ceiba.BasePrueba;
import com.ceiba.dominio.excepcion.ExcepcionSinDatos;
import com.ceiba.propietario.puerto.repositorio.RepositorioPropietario;
import com.ceiba.propietario.servicio.ServiciosEliminarPropietario;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.when;

public class ServicioEliminarPropietarioTest {

    private static final String NOT_EXISTS = "El propietario no existe en el sistema";

    @Test
    void deberiaEliminarPropietarioExistosamente(){
        //arrange
        RepositorioPropietario repositorioPropietario = Mockito.mock(RepositorioPropietario.class);
        when(repositorioPropietario.existeConId(anyLong())).thenReturn(true);
        ServiciosEliminarPropietario serviciosEliminarPropietario = new ServiciosEliminarPropietario(repositorioPropietario);
        //act
        serviciosEliminarPropietario.ejecutar(1L);
        //assert
        Mockito.verify(repositorioPropietario, Mockito.times(1)).eliminar(1L);
    }

    @Test
    void eliminarPropietarioDebeFallar(){
        //arrange
        RepositorioPropietario repositorioPropietario = Mockito.mock(RepositorioPropietario.class);
        when(repositorioPropietario.existeConId(anyLong())).thenReturn(false);
        ServiciosEliminarPropietario serviciosEliminarPropietario = new ServiciosEliminarPropietario(repositorioPropietario);
        //act - assert
        BasePrueba.assertThrows(()-> serviciosEliminarPropietario.ejecutar(1L),
                ExcepcionSinDatos.class, NOT_EXISTS);
    }
}
