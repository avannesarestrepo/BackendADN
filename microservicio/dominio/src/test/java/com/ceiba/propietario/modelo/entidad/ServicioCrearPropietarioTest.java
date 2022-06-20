package com.ceiba.propietario.modelo.entidad;

import com.ceiba.BasePrueba;
import com.ceiba.dominio.excepcion.ExcepcionDuplicidad;
import com.ceiba.propietario.puerto.repositorio.RepositorioPropietario;
import com.ceiba.propietario.servicio.ServicioCrearPropietario;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;

public class ServicioCrearPropietarioTest {

    @Test
    void deberiaCrearPropietario(){
        RepositorioPropietario repositorioPropietario = Mockito.mock(RepositorioPropietario.class);
        when(repositorioPropietario.guardar(Mockito.any())).thenReturn(1L);
        Propietario propietario = new PropietarioTestDataBuilder().conPropietarioPorDefecto().reconstruir();
        ServicioCrearPropietario servicioCrearPropietario = new ServicioCrearPropietario(repositorioPropietario);

        Long id = servicioCrearPropietario.ejecutar(propietario);

        Assertions.assertEquals(1L, id);
    }

    @Test
    void crearPropietarioDebeFallar(){
        Propietario propietario = new PropietarioTestDataBuilder().conPropietarioPorDefecto().build();
        RepositorioPropietario repositorioPropietario = Mockito.mock(RepositorioPropietario.class);
        when(repositorioPropietario.existeConDocumento(anyString())).thenReturn(true);

        ServicioCrearPropietario servicioCrearPropietario = new ServicioCrearPropietario(repositorioPropietario);

        BasePrueba.assertThrows(() -> servicioCrearPropietario.ejecutar(propietario),
                ExcepcionDuplicidad.class, "El propietario ya existe");
    }
}
