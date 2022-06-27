package com.ceiba.factura.modelo.servicio;

import com.ceiba.factura.modelo.entidad.Factura;
import com.ceiba.factura.modelo.entidad.FacturaTestDataBuilder;
import com.ceiba.factura.puerto.repositorio.RepositorioFactura;
import com.ceiba.factura.servicio.ServicioModificarFactura;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

public class ServicioModificarFacturaTest {

    @Test
    void modificarFacturaExitosamente(){
        RepositorioFactura repositorioFactura = Mockito.mock(RepositorioFactura.class);

        ServicioModificarFactura servicioModificarFactura = new ServicioModificarFactura(repositorioFactura);
        Factura factura = new FacturaTestDataBuilder().crearFacturaMensual();
        servicioModificarFactura.ejecutar(factura);

        Mockito.verify(repositorioFactura, Mockito.times(1)).actualizar(factura);

    }
}
