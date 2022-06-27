package com.ceiba.propietario.servicio;

import com.ceiba.propietario.puerto.repositorio.RepositorioPropietario;
import com.ceiba.propietario.servicio.ServicioEliminarPropietario;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

public class ServicioEliminarPropietarioTest {

    private static final String NOT_EXISTS = "El propietario no existe en el sistema";

    @Test
    void eliminarPropietarioExistosamente(){
        //arrange
        RepositorioPropietario repositorioPropietario = Mockito.mock(RepositorioPropietario.class);
        ServicioEliminarPropietario serviciosEliminarPropietario = new ServicioEliminarPropietario(repositorioPropietario);
        //act
        serviciosEliminarPropietario.ejecutar("1000678940");
        //assert
        Mockito.verify(repositorioPropietario, Mockito.times(1)).eliminar("1000678940");
    }
}
