package com.ceiba.factura.modelo.entidad;

import com.ceiba.BasePrueba;
import com.ceiba.dominio.excepcion.ExcepcionSinDatos;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.time.LocalDateTime;

public class FacturaTest {

    private static final String VEHICULO_OBLIGATORIO = "El Vehiculo es obligatorio";

    private static final String PLACA_OBLIGATORIO = "El campo 'placa' para esta modalidad es obligatorio";


    @Test
    void crearFacturaMensualCorrectamente() {
        Factura factura = new FacturaTestDataBuilder().crearFacturaMensual();

        Assertions.assertEquals(TipoModalidad.MENSUAL, factura.getTipoModalidad());
        Assertions.assertEquals("RTE024", factura.getVehiculo().getPlaca());
        Assertions.assertEquals(BigDecimal.valueOf(176000), factura.getValor());
        Assertions.assertTrue(factura.esFacturaConfirmada());
    }

    @Test
    void crearFacturaQuincenalCorrectamente(){
        Factura factura = new FacturaTestDataBuilder().crearFacturaQuincenal();

        Assertions.assertEquals(TipoModalidad.QUINCENAL, factura.getTipoModalidad());
        Assertions.assertEquals("RTE024", factura.getVehiculo().getPlaca());
        Assertions.assertEquals(BigDecimal.valueOf(104000), factura.getValor());
        Assertions.assertTrue(factura.esFacturaConfirmada());
    }

    @Test
    void crearFacturaDiaCorrectamente(){
        Factura factura = new FacturaTestDataBuilder().crearFacturaDia();

        Assertions.assertEquals(TipoModalidad.DIA, factura.getTipoModalidad());
        Assertions.assertEquals(BigDecimal.valueOf(8000), factura.getValor());
        Assertions.assertTrue(factura.esFacturaConfirmada());
    }

    @Test
    void crearFacturaHoraCorrectamente(){
        Factura factura = new FacturaTestDataBuilder().crearFacturaHora();

        Assertions.assertEquals(TipoModalidad.HORA, factura.getTipoModalidad());
        Assertions.assertTrue(factura.esFacturaPendiente());
    }

    @Test
    void confirmarFacturaHoraCorrectamente(){
        Factura facturaSinConfirmar = new FacturaTestDataBuilder().crearFacturaHora();

        Factura factura = new FacturaTestDataBuilder()
                        .conId(1L)
                        .conTipoModalidad(facturaSinConfirmar.getTipoModalidad())
                        .conVehiculo(facturaSinConfirmar.getVehiculo())
                        .conFechaInicio(facturaSinConfirmar.getFechaInicio())
                        .conPlaca(facturaSinConfirmar.getPlaca())
                        .conEstadoFactura(EstadoFactura.CONFIRMADA).reconstruir();

        Assertions.assertEquals(1L, factura.getId());
        Assertions.assertEquals(TipoModalidad.HORA, factura.getTipoModalidad());
        Assertions.assertTrue(factura.esFacturaConfirmada());
        Assertions.assertEquals("XXX000", factura.getPlaca());
    }

    @Test
    void crearFacturaMensualSinVehiculo(){
        Factura facturaMensual = new FacturaTestDataBuilder().crearFacturaMensual();

        BasePrueba.assertThrows(() -> new FacturaTestDataBuilder()
                .conId(1L)
                .conTipoModalidad(facturaMensual.getTipoModalidad())
                .conVehiculo(null)
                .conFechaInicio(facturaMensual.getFechaInicio())
                .conFechaFin(LocalDateTime.now())
                .conValor(BigDecimal.valueOf(160000))
                .conPlaca(facturaMensual.getPlaca())
                .conEstadoFactura(facturaMensual.getEstadoFactura()).reconstruir(),
                ExcepcionSinDatos.class,
                VEHICULO_OBLIGATORIO);
    }

    @Test
    void crearFacturaQuincenalSinVehiculo(){
        Factura facturaMensual = new FacturaTestDataBuilder().crearFacturaQuincenal();

        BasePrueba.assertThrows(() -> new FacturaTestDataBuilder()
                        .conId(1L)
                        .conTipoModalidad(facturaMensual.getTipoModalidad())
                        .conVehiculo(null)
                        .conFechaInicio(facturaMensual.getFechaInicio())
                        .conPlaca(facturaMensual.getPlaca())
                        .conEstadoFactura(facturaMensual.getEstadoFactura()).reconstruir(),
                ExcepcionSinDatos.class,
                VEHICULO_OBLIGATORIO);
    }

    @Test
    void crearFacturaDiaSinPlaca(){
        Factura facturaMensual = new FacturaTestDataBuilder().crearFacturaDia();

        BasePrueba.assertThrows(() -> new FacturaTestDataBuilder()
                        .conId(1L)
                        .conTipoModalidad(facturaMensual.getTipoModalidad())
                        .conFechaInicio(facturaMensual.getFechaInicio())
                        .conPlaca("")
                        .conEstadoFactura(facturaMensual.getEstadoFactura()).reconstruir(),
                ExcepcionSinDatos.class,
                PLACA_OBLIGATORIO);
    }

    @Test
    void crearFacturaHoraSinPlaca(){
        Factura facturaMensual = new FacturaTestDataBuilder().crearFacturaHora();

        BasePrueba.assertThrows(() -> new FacturaTestDataBuilder()
                        .conId(1L)
                        .conTipoModalidad(facturaMensual.getTipoModalidad())
                        .conFechaInicio(facturaMensual.getFechaInicio())
                        .conPlaca("")
                        .conEstadoFactura(facturaMensual.getEstadoFactura()).reconstruir(),
                ExcepcionSinDatos.class,
                PLACA_OBLIGATORIO);
    }
}
