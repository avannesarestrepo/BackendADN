package com.ceiba.factura.modelo.entidad;

import com.ceiba.BasePrueba;
import com.ceiba.dominio.excepcion.ExcepcionSinDatos;
import com.ceiba.dominio.excepcion.ExcepcionValorObligatorio;
import com.ceiba.factura.puerto.repositorio.RepositorioFactura;
import com.ceiba.factura.servicio.ServicioCrearFactura;
import com.ceiba.propietario.puerto.dao.DaoPropietario;
import com.ceiba.vehiculo.puerto.dao.DaoVehiculo;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.when;

public class ServicioCrearFacturaTest {

    @Test
    void deberiaCrearFactura(){
        //assets
        RepositorioFactura repositorioFactura = Mockito.mock(RepositorioFactura.class);
        DaoPropietario daoPropietario = Mockito.mock(DaoPropietario.class);
        DaoVehiculo daoVehiculo = Mockito.mock(DaoVehiculo.class);

        when(repositorioFactura.guardar(Mockito.any())).thenReturn(1L);
        when(daoPropietario.existePorId(anyLong())).thenReturn(true);
        when(daoVehiculo.existePorId(anyLong())).thenReturn(true);

        Factura factura = new FacturaTestDataBuilder().conFacturaMensualPorDefecto().build();
        ServicioCrearFactura servicioCrearFactura = new ServicioCrearFactura(repositorioFactura, daoVehiculo, daoPropietario);

        //act
        Long id = servicioCrearFactura.ejecutar(factura);

        //Asserts
        Assertions.assertEquals(1L, id);
    }

    @Test
    void crearFacturaMensualSinPropietarioDebeFallar(){
        Factura factura = new FacturaTestDataBuilder().conFacturaMensualPorDefecto().build();
        RepositorioFactura repositorioFactura = Mockito.mock(RepositorioFactura.class);
        DaoPropietario daoPropietario = Mockito.mock(DaoPropietario.class);
        DaoVehiculo daoVehiculo = Mockito.mock(DaoVehiculo.class);

        when(daoPropietario.existePorId(anyLong())).thenReturn(false);
        when(daoVehiculo.existePorId(anyLong())).thenReturn(true);

        ServicioCrearFactura servicioCrearFactura = new ServicioCrearFactura(repositorioFactura, daoVehiculo, daoPropietario);


        BasePrueba.assertThrows(() -> servicioCrearFactura.ejecutar(factura),
                ExcepcionSinDatos.class, "El propietario relacionado a la factura no existe");
    }

    @Test
    void crearFacturaMensualSinVehiculoDebeFallar(){
        Factura factura = new FacturaTestDataBuilder().conFacturaMensualPorDefecto().build();
        RepositorioFactura repositorioFactura = Mockito.mock(RepositorioFactura.class);
        DaoPropietario daoPropietario = Mockito.mock(DaoPropietario.class);
        DaoVehiculo daoVehiculo = Mockito.mock(DaoVehiculo.class);

        when(daoPropietario.existePorId(anyLong())).thenReturn(true);
        when(daoVehiculo.existePorId(anyLong())).thenReturn(false);

        ServicioCrearFactura servicioCrearFactura = new ServicioCrearFactura(repositorioFactura, daoVehiculo, daoPropietario);


        BasePrueba.assertThrows(() -> servicioCrearFactura.ejecutar(factura),
                ExcepcionSinDatos.class, "El vehiculo relacionado a la factura no existe");
    }

    @Test
    void crearFacturaDiaSinDescripcionDebeFallar(){
        Factura factura = new FacturaTestDataBuilder().conFacturaDiaSinDescripcionPorDefecto().build();
        RepositorioFactura repositorioFactura = Mockito.mock(RepositorioFactura.class);
        DaoPropietario daoPropietario = Mockito.mock(DaoPropietario.class);
        DaoVehiculo daoVehiculo = Mockito.mock(DaoVehiculo.class);

        ServicioCrearFactura servicioCrearFactura = new ServicioCrearFactura(repositorioFactura, daoVehiculo, daoPropietario);

        BasePrueba.assertThrows(() -> servicioCrearFactura.ejecutar(factura),
                ExcepcionValorObligatorio.class, "El campo 'descripcion' para la modalidad asignada es obligatorio");
    }

    @Test
    void crearFacturaDiaDescripcionVaciaDebeFallar(){
        Factura factura = new FacturaTestDataBuilder().conFacturaDiaDescripcionVaciaPorDefecto().build();
        RepositorioFactura repositorioFactura = Mockito.mock(RepositorioFactura.class);
        DaoPropietario daoPropietario = Mockito.mock(DaoPropietario.class);
        DaoVehiculo daoVehiculo = Mockito.mock(DaoVehiculo.class);

        ServicioCrearFactura servicioCrearFactura = new ServicioCrearFactura(repositorioFactura, daoVehiculo, daoPropietario);

        BasePrueba.assertThrows(() -> servicioCrearFactura.ejecutar(factura),
                ExcepcionValorObligatorio.class, "El campo 'descripcion' para la modalidad asignada es obligatorio");
    }

}
