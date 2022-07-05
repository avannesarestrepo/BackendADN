package com.ceiba.factura.modelo.entidad;

import com.ceiba.vehiculo.modelo.entidad.Vehiculo;
import com.ceiba.vehiculo.modelo.entidad.VehiculoTestDataBuilder;

import java.math.BigDecimal;
import java.time.LocalDateTime;

public class FacturaTestDataBuilder {
    private Long id;
    private TipoModalidad tipoModalidad;
    private Vehiculo vehiculo;
    private LocalDateTime fechaInicio;
    private LocalDateTime fechaFin;
    private BigDecimal valor;
    private String placa;
    private EstadoFactura estadoFactura;

    public FacturaTestDataBuilder conFacturaPorDefecto(){
        this.id = 1L;
        this.tipoModalidad = TipoModalidad.MENSUAL;
        this.vehiculo = new VehiculoTestDataBuilder().conVehiculoPorDefecto().build();
        this.fechaInicio = LocalDateTime.now();
        this.fechaFin = LocalDateTime.now().plusDays(30);
        this.valor = BigDecimal.valueOf(168000);
        this.placa = "";
        this.estadoFactura = EstadoFactura.CONFIRMADA;
        return this;
    }

    public Factura crearFacturaMensual(){
        return Factura.crear(new SolicitarFacturaTestDataBuilder()
                .solicitarFacturaMensualPorDefecto().build());
    }

    public Factura crearFacturaQuincenal(){
        return Factura.crear(new SolicitarFacturaTestDataBuilder()
                .solicitarFacturaQuincenalPorDefecto().build());
    }

    public Factura crearFacturaDia(){
        return Factura.crear(new SolicitarFacturaTestDataBuilder()
                .solicitarFacturaDiaPorDefecto().build());
    }

    public Factura crearFacturaMensualSinVehiculo(){
        return Factura.crear(new SolicitarFacturaTestDataBuilder()
                .solicitarFacturaMensualSinVehiculoPorDefecto().build());
    }

    public Factura crearFacturaHora(){
        return Factura.crear(new SolicitarFacturaTestDataBuilder()
                .solicitarFacturaHoraPorDefecto().build());
    }

    public Factura reconstruir(){
        return Factura.reconstruir(id, tipoModalidad, vehiculo, fechaInicio, placa, estadoFactura);
    }

    public FacturaTestDataBuilder conId(Long id){
        this.id = id;
        return this;
    }

    public FacturaTestDataBuilder conTipoModalidad(TipoModalidad tipoModalidad){
        this.tipoModalidad = tipoModalidad;
        return this;
    }

    public FacturaTestDataBuilder conVehiculo(Vehiculo vehiculo){
        this.vehiculo = vehiculo;
        return this;
    }

    public FacturaTestDataBuilder conFechaInicio(LocalDateTime fechaInicio){
        this.fechaInicio = fechaInicio;
        return this;
    }

    public FacturaTestDataBuilder conFechaFin(LocalDateTime fechaFin){
        this.fechaFin = fechaFin;
        return this;
    }

    public FacturaTestDataBuilder conValor(BigDecimal valor){
        this.valor = valor;
        return this;
    }

    public FacturaTestDataBuilder conPlaca(String placa){
        this.placa = placa;
        return this;
    }

    public FacturaTestDataBuilder conEstadoFactura(EstadoFactura estadoFactura){
        this.estadoFactura = estadoFactura;
        return this;
    }
}
