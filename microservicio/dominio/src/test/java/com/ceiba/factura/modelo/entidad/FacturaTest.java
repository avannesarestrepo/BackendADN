package com.ceiba.factura.modelo.entidad;

import com.ceiba.factura.modelo.dto.FacturaDTO;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class FacturaTest {

    @Test
    void crearFacturaMensualExitosa(){
        //Arrange - Act
        Factura factura = new FacturaTestDataBuilder().conFacturaMensualPorDefecto().build();
        //Assert
        Assertions.assertEquals(1L, factura.getIdFactura());
        Assertions.assertEquals(TipoModalidadFactura.MENSUAL, factura.getTipoModalidad());
        Assertions.assertEquals(1L, factura.getIdPropietario());
        Assertions.assertEquals(1L, factura.getIdVehiculo());
        Assertions.assertTrue(factura.getFechaInicio() != null);
        Assertions.assertTrue(factura.getFechaFin() != null);
        Assertions.assertEquals(160000D, factura.getValor());
        Assertions.assertEquals(true, factura.getEstado());
    }

    @Test
    void crearFacturaQuincenalExitosa(){
        //Arrange - Act
        Factura factura = new FacturaTestDataBuilder().conFacturaQuincenalPorDefecto().build();
        //Assert
        Assertions.assertEquals(2L, factura.getIdFactura());
        Assertions.assertEquals(TipoModalidadFactura.QUINCENAL, factura.getTipoModalidad());
        Assertions.assertEquals(1L, factura.getIdPropietario());
        Assertions.assertEquals(1L, factura.getIdVehiculo());
        Assertions.assertTrue(factura.getFechaInicio() != null);
        Assertions.assertTrue(factura.getFechaFin() != null);
        Assertions.assertEquals(104000D, factura.getValor());
        Assertions.assertEquals(true, factura.getEstado());
    }

    @Test
    void crearFacturaDiaExitosa(){
        //Arrange - Act
        Factura factura = new FacturaTestDataBuilder().conFacturaDiaPorDefecto().build();
        //Assert
        Assertions.assertEquals(3L, factura.getIdFactura());
        Assertions.assertEquals(TipoModalidadFactura.DIA, factura.getTipoModalidad());
        Assertions.assertTrue(factura.getFechaInicio() != null);
        Assertions.assertTrue(factura.getFechaFin() != null);
        Assertions.assertEquals(8000D, factura.getValor());
        Assertions.assertEquals("PRD09F", factura.getDescripcion());
        Assertions.assertEquals(true, factura.getEstado());
    }

    @Test
    void crearFacturaHoraExitosa(){
        //Arrange - Act
        Factura factura = new FacturaTestDataBuilder().conFacturaHoraPorDefecto().build();
        //Assert
        Assertions.assertEquals(4L, factura.getIdFactura());
        Assertions.assertEquals(TipoModalidadFactura.HORA, factura.getTipoModalidad());
        Assertions.assertTrue(factura.getFechaInicio() != null);
        Assertions.assertTrue(factura.getFechaFin() != null);
        Assertions.assertEquals(1000D, factura.getValor());
        Assertions.assertEquals("TDX094", factura.getDescripcion());
        Assertions.assertEquals(true, factura.getEstado());
    }
}
