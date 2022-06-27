package com.ceiba.factura.modelo.entidad;

import com.ceiba.vehiculo.modelo.entidad.Vehiculo;
import com.ceiba.vehiculo.modelo.entidad.VehiculoTestDataBuilder;

import java.time.LocalDateTime;

public class SolicitarFacturaTestDataBuilder {
    private TipoModalidad tipoModalidad;
    private Vehiculo vehiculo;
    private LocalDateTime fechaInicio;
    private String placa;

    public SolicitarFacturaTestDataBuilder solicitarFacturaMensualPorDefecto(){
        this.tipoModalidad = TipoModalidad.MENSUAL;
        this.vehiculo = new VehiculoTestDataBuilder().conVehiculoPorDefecto().build();
        this.fechaInicio = LocalDateTime.now();
        return this;
    }

    public SolicitarFacturaTestDataBuilder solicitarFacturaQuincenalPorDefecto(){
        this.tipoModalidad = TipoModalidad.QUINCENAL;
        this.vehiculo = new VehiculoTestDataBuilder().conVehiculoPorDefecto().build();
        this.fechaInicio = LocalDateTime.now();
        return this;
    }

    public SolicitarFacturaTestDataBuilder solicitarFacturaDiaPorDefecto(){
        this.tipoModalidad = TipoModalidad.DIA;
        this.fechaInicio = LocalDateTime.now();
        this.placa = "XXX000";
        return this;
    }

    public SolicitarFacturaTestDataBuilder solicitarFacturaHoraPorDefecto(){
        this.tipoModalidad = TipoModalidad.HORA;
        this.fechaInicio = LocalDateTime.now();
        this.placa ="XXX000";
        return this;
    }

    public SolicitarFacturaTestDataBuilder solicitarFacturaMensualSinVehiculoPorDefecto(){
        this.tipoModalidad = tipoModalidad.MENSUAL;
        this.fechaInicio = LocalDateTime.now();
        return this;
    }

    public SolicitarFactura build(){
        return new SolicitarFactura(tipoModalidad, vehiculo, fechaInicio, placa);
    }
}
