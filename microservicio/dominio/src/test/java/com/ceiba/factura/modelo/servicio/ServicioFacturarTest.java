package com.ceiba.factura.modelo.servicio;

import com.ceiba.factura.modelo.entidad.*;
import com.ceiba.factura.puerto.repositorio.RepositorioFactura;
import com.ceiba.factura.servicio.ServicioFacturar;
import com.ceiba.vehiculo.modelo.entidad.Vehiculo;
import com.ceiba.vehiculo.modelo.entidad.VehiculoTestDataBuilder;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.Mockito;

import java.math.BigDecimal;

public class ServicioFacturarTest {

    @Test
    void crearFacturaExitosamente(){
        RepositorioFactura repositorioFactura = Mockito.mock(RepositorioFactura.class);
        Mockito.when(repositorioFactura.crear(Mockito.any())).thenReturn(1L);

        Vehiculo vehiculo = new VehiculoTestDataBuilder().conVehiculoPorDefecto().build();

        SolicitarFactura solicitarFactura = new SolicitarFacturaTestDataBuilder()
                .solicitarFacturaMensualPorDefecto().build();

        ServicioFacturar servicioFacturar = new ServicioFacturar(repositorioFactura);

        Long id = servicioFacturar.ejecutar(solicitarFactura);

        ArgumentCaptor<Factura> captorFactura = ArgumentCaptor.forClass(Factura.class);
        Mockito.verify(repositorioFactura, Mockito.times(1)).crear(captorFactura.capture());
        Assertions.assertEquals(1L, id);
        Assertions.assertEquals(TipoModalidad.MENSUAL, captorFactura.getValue().getTipoModalidad());
        Assertions.assertEquals(vehiculo.getId(), captorFactura.getValue().getVehiculo().getId());
        Assertions.assertEquals(BigDecimal.valueOf(176000), captorFactura.getValue().getValor());
        Assertions.assertEquals(EstadoFactura.CONFIRMADA, captorFactura.getValue().getEstadoFactura());
    }
}
