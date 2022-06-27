package com.ceiba.factura.modelo.servicio;

import com.ceiba.factura.modelo.entidad.Factura;
import com.ceiba.factura.modelo.entidad.FacturaTestDataBuilder;
import com.ceiba.factura.puerto.repositorio.RepositorioFactura;
import com.ceiba.factura.servicio.ServicioAnularFactura;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

public class ServicioAnularFacturaTest {

    @Test
    void anularFacturaExitosamente(){
        RepositorioFactura repositorioFactura = Mockito.mock(RepositorioFactura.class);

        ServicioAnularFactura servicioAnularFactura = new ServicioAnularFactura(repositorioFactura);
        Factura factura = new FacturaTestDataBuilder().crearFacturaMensual();

        servicioAnularFactura.ejecutar(factura);
        Mockito.verify(repositorioFactura, Mockito.times(1)).anular(factura.getId());

    }
}
