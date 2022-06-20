package com.ceiba.factura.modelo.entidad;

import com.ceiba.BasePrueba;
import com.ceiba.dominio.excepcion.ExcepcionSinDatos;
import com.ceiba.dominio.excepcion.ExcepcionValorObligatorio;
import com.ceiba.factura.puerto.repositorio.RepositorioFactura;
import com.ceiba.factura.servicio.ServicioActualizarFactura;
import com.ceiba.propietario.puerto.dao.DaoPropietario;
import com.ceiba.vehiculo.puerto.dao.DaoVehiculo;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.when;

public class ServicioActualizarFacturaTest {

    @Test
    void deberiaActualizarFactura(){
        //assets
        RepositorioFactura repositorioFactura = Mockito.mock(RepositorioFactura.class);
        DaoPropietario daoPropietario = Mockito.mock(DaoPropietario.class);
        DaoVehiculo daoVehiculo = Mockito.mock(DaoVehiculo.class);

        when(repositorioFactura.existeConId(anyLong())).thenReturn(true);
        when(daoPropietario.existePorId(anyLong())).thenReturn(true);
        when(daoVehiculo.existePorId(anyLong())).thenReturn(true);

        Factura factura = new FacturaTestDataBuilder().conFacturaMensualPorDefecto().build();
        ServicioActualizarFactura servicioActualizarFactura = new ServicioActualizarFactura(repositorioFactura, daoVehiculo, daoPropietario);

        //act
        servicioActualizarFactura.ejecutar(factura);

        //Asserts
        Mockito.verify(repositorioFactura, Mockito.times(1)).actualizar(factura);
    }

    @Test
    void actualizarFacturaMensualSinPropietarioDebeFallar(){
        Factura factura = new FacturaTestDataBuilder().conFacturaMensualPorDefecto().build();
        RepositorioFactura repositorioFactura = Mockito.mock(RepositorioFactura.class);
        DaoPropietario daoPropietario = Mockito.mock(DaoPropietario.class);
        DaoVehiculo daoVehiculo = Mockito.mock(DaoVehiculo.class);

        when(repositorioFactura.existeConId(anyLong())).thenReturn(true);
        when(daoPropietario.existePorId(anyLong())).thenReturn(false);
        when(daoVehiculo.existePorId(anyLong())).thenReturn(true);

        ServicioActualizarFactura servicioActualizarFactura = new ServicioActualizarFactura(repositorioFactura, daoVehiculo, daoPropietario);


        BasePrueba.assertThrows(() -> servicioActualizarFactura.ejecutar(factura),
                ExcepcionSinDatos.class, "El propietario relacionado a la factura no existe");
    }

    @Test
    void actualizarFacturaQuincenalSinPropietarioDebeFallar(){
        Factura factura = new FacturaTestDataBuilder().conFacturaQuincenalPorDefecto().build();
        RepositorioFactura repositorioFactura = Mockito.mock(RepositorioFactura.class);
        DaoPropietario daoPropietario = Mockito.mock(DaoPropietario.class);
        DaoVehiculo daoVehiculo = Mockito.mock(DaoVehiculo.class);

        when(repositorioFactura.existeConId(anyLong())).thenReturn(true);
        when(daoPropietario.existePorId(anyLong())).thenReturn(false);
        when(daoVehiculo.existePorId(anyLong())).thenReturn(true);

        ServicioActualizarFactura servicioActualizarFactura = new ServicioActualizarFactura(repositorioFactura, daoVehiculo, daoPropietario);


        BasePrueba.assertThrows(() -> servicioActualizarFactura.ejecutar(factura),
                ExcepcionSinDatos.class, "El propietario relacionado a la factura no existe");
    }

    @Test
    void actualizarFacturaMensualSinVehiculoDebeFallar(){
        Factura factura = new FacturaTestDataBuilder().conFacturaMensualPorDefecto().build();
        RepositorioFactura repositorioFactura = Mockito.mock(RepositorioFactura.class);
        DaoPropietario daoPropietario = Mockito.mock(DaoPropietario.class);
        DaoVehiculo daoVehiculo = Mockito.mock(DaoVehiculo.class);

        when(repositorioFactura.existeConId(anyLong())).thenReturn(true);
        when(daoPropietario.existePorId(anyLong())).thenReturn(true);
        when(daoVehiculo.existePorId(anyLong())).thenReturn(false);

        ServicioActualizarFactura servicioActualizarFactura = new ServicioActualizarFactura(repositorioFactura, daoVehiculo, daoPropietario);

        BasePrueba.assertThrows(() -> servicioActualizarFactura.ejecutar(factura),
                ExcepcionSinDatos.class, "El vehiculo relacionado a la factura no existe");
    }

    @Test
    void actualizarFacturaDiaSinDescripcionDebeFallar(){
        Factura factura = new FacturaTestDataBuilder().conFacturaDiaSinDescripcionPorDefecto().build();
        RepositorioFactura repositorioFactura = Mockito.mock(RepositorioFactura.class);
        DaoPropietario daoPropietario = Mockito.mock(DaoPropietario.class);
        DaoVehiculo daoVehiculo = Mockito.mock(DaoVehiculo.class);

        when(repositorioFactura.existeConId(anyLong())).thenReturn(true);

        ServicioActualizarFactura servicioActualizarFactura = new ServicioActualizarFactura(repositorioFactura, daoVehiculo, daoPropietario);

        BasePrueba.assertThrows(() -> servicioActualizarFactura.ejecutar(factura),
                ExcepcionValorObligatorio.class, "El campo 'descripcion' para la modalidad asignada es obligatorio");
    }

    @Test
    void actualizarFacturaHoraSinDescripcionDebeFallar(){
        Factura factura = new FacturaTestDataBuilder().conFacturaHoraSinDescripcionPorDefecto().build();
        RepositorioFactura repositorioFactura = Mockito.mock(RepositorioFactura.class);
        DaoPropietario daoPropietario = Mockito.mock(DaoPropietario.class);
        DaoVehiculo daoVehiculo = Mockito.mock(DaoVehiculo.class);

        when(repositorioFactura.existeConId(anyLong())).thenReturn(true);

        ServicioActualizarFactura servicioActualizarFactura = new ServicioActualizarFactura(repositorioFactura, daoVehiculo, daoPropietario);

        BasePrueba.assertThrows(() -> servicioActualizarFactura.ejecutar(factura),
                ExcepcionValorObligatorio.class, "El campo 'descripcion' para la modalidad asignada es obligatorio");
    }

    @Test
    void actualizarFacturaDiaDescripcionVaciaDebeFallar(){
        Factura factura = new FacturaTestDataBuilder().conFacturaDiaDescripcionVaciaPorDefecto().build();
        RepositorioFactura repositorioFactura = Mockito.mock(RepositorioFactura.class);
        DaoPropietario daoPropietario = Mockito.mock(DaoPropietario.class);
        DaoVehiculo daoVehiculo = Mockito.mock(DaoVehiculo.class);

        when(repositorioFactura.existeConId(anyLong())).thenReturn(true);

        ServicioActualizarFactura servicioActualizarFactura = new ServicioActualizarFactura(repositorioFactura, daoVehiculo, daoPropietario);

        BasePrueba.assertThrows(() -> servicioActualizarFactura.ejecutar(factura),
                ExcepcionValorObligatorio.class, "El campo 'descripcion' para la modalidad asignada es obligatorio");
    }

    @Test
    void actualizarFacturaHoraDescripcionVaciaDebeFallar(){
        Factura factura = new FacturaTestDataBuilder().conFacturaHoraDescripcionVaciaPorDefecto().build();
        RepositorioFactura repositorioFactura = Mockito.mock(RepositorioFactura.class);
        DaoPropietario daoPropietario = Mockito.mock(DaoPropietario.class);
        DaoVehiculo daoVehiculo = Mockito.mock(DaoVehiculo.class);

        when(repositorioFactura.existeConId(anyLong())).thenReturn(true);

        ServicioActualizarFactura servicioActualizarFactura = new ServicioActualizarFactura(repositorioFactura, daoVehiculo, daoPropietario);

        BasePrueba.assertThrows(() -> servicioActualizarFactura.ejecutar(factura),
                ExcepcionValorObligatorio.class, "El campo 'descripcion' para la modalidad asignada es obligatorio");
    }

    @Test
    void  actualizarFacturaPorIdDebeFallar(){
        //arrange
        Factura factura = new FacturaTestDataBuilder().conFacturaMensualPorDefecto().build();
        RepositorioFactura repositorioFactura = Mockito.mock(RepositorioFactura.class);
        DaoPropietario daoPropietario = Mockito.mock(DaoPropietario.class);
        DaoVehiculo daoVehiculo = Mockito.mock(DaoVehiculo.class);
        when(repositorioFactura.existeConId(anyLong())).thenReturn(false);
        ServicioActualizarFactura servicioActualizarFactura = new ServicioActualizarFactura(repositorioFactura, daoVehiculo, daoPropietario);
        //act - assert
        BasePrueba.assertThrows(()-> servicioActualizarFactura.ejecutar(factura),
                ExcepcionSinDatos.class, "La factura no existe en el sistema");
    }

}
