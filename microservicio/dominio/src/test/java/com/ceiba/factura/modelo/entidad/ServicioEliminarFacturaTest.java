package com.ceiba.factura.modelo.entidad;

import com.ceiba.BasePrueba;
import com.ceiba.dominio.excepcion.ExcepcionSinDatos;
import com.ceiba.factura.puerto.repositorio.RepositorioFactura;
import com.ceiba.factura.servicio.ServicioEliminarFactura;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.when;

public class ServicioEliminarFacturaTest {

    private static final String NOT_EXISTS = "La factura no existe en el sistema";

    @Test
    void deberiaEliminarFacturaExitosamente(){
        RepositorioFactura repositorioFactura = Mockito.mock(RepositorioFactura.class);
        when(repositorioFactura.existeConId(anyLong())).thenReturn(true);
        ServicioEliminarFactura servicioEliminarFactura = new ServicioEliminarFactura(repositorioFactura);

        servicioEliminarFactura.ejecutar(1L);

        Mockito.verify(repositorioFactura,Mockito.times(1)).eliminar(1L);
    }

    @Test
    void eliminarFacturaDebeFallar(){
        //arrange
        RepositorioFactura repositorioFactura = Mockito.mock(RepositorioFactura.class);
        when(repositorioFactura.existeConId(anyLong())).thenReturn(false);
        ServicioEliminarFactura servicioEliminarFactura = new ServicioEliminarFactura(repositorioFactura);
        //act - assert
        BasePrueba.assertThrows(()-> servicioEliminarFactura.ejecutar(1L),
                ExcepcionSinDatos.class, NOT_EXISTS);
    }
}
