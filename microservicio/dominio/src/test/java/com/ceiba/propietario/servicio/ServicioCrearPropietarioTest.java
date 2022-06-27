package com.ceiba.propietario.servicio;

import com.ceiba.BasePrueba;
import com.ceiba.dominio.excepcion.ExcepcionDuplicidad;
import com.ceiba.propietario.modelo.entidad.Propietario;
import com.ceiba.propietario.modelo.entidad.PropietarioTestDataBuilder;
import com.ceiba.propietario.puerto.repositorio.RepositorioPropietario;
import com.ceiba.propietario.servicio.ServicioCrearPropietario;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;

public class ServicioCrearPropietarioTest {

    @Test
    void crearPropietarioExitosamente(){
        RepositorioPropietario repositorioPropietario = Mockito.mock(RepositorioPropietario.class);
        when(repositorioPropietario.crear(any())).thenReturn(1L);
        Propietario propietario = new PropietarioTestDataBuilder().conPropietarioPorDefecto().build();
        ServicioCrearPropietario servicioCrearPropietario = new ServicioCrearPropietario(repositorioPropietario);

        Long id = servicioCrearPropietario.ejecutar(propietario);

        Assertions.assertEquals(1L, id);
    }

    @Test
    void crearPropietarioDebeFallar(){
        Propietario propietario = new PropietarioTestDataBuilder().conPropietarioPorDefecto().build();
        RepositorioPropietario repositorioPropietario = Mockito.mock(RepositorioPropietario.class);
        when(repositorioPropietario.existePropietario(any())).thenReturn(true);

        ServicioCrearPropietario servicioCrearPropietario = new ServicioCrearPropietario(repositorioPropietario);

        BasePrueba.assertThrows(() -> servicioCrearPropietario.ejecutar(propietario),
                ExcepcionDuplicidad.class, "El propietario ya existe");
    }
}
