package com.ceiba.factura.modelo.entidad;

import com.ceiba.factura.modelo.dto.FacturaDTO;

import java.time.LocalDateTime;

public class FacturaTestDataBuilder {
    private Long idFactura;
    private TipoModalidadFactura tipoModalidad;
    private Long idPropietario;
    private Long idVehiculo;
    private LocalDateTime fechaInicio;
    private LocalDateTime fechaFin;
    private Double valor;
    private String descripcion;
    private Boolean estado;

    public FacturaTestDataBuilder conFacturaMensualPorDefecto(){
        this.idFactura = 1L;
        this.tipoModalidad = TipoModalidadFactura.MENSUAL;
        this.idPropietario = 1L;
        this.idVehiculo = 1L;
        this.fechaInicio = LocalDateTime.now();
        this.fechaFin = LocalDateTime.now().plusDays(30);
        this.valor = 168000D;
        this.descripcion = "";
        this.estado = true;
        return this;
    }

    public FacturaTestDataBuilder conFacturaQuincenalPorDefecto(){
        this.idFactura = 2L;
        this.tipoModalidad = TipoModalidadFactura.QUINCENAL;
        this.idPropietario = 1L;
        this.idVehiculo = 1L;
        this.fechaInicio = LocalDateTime.now();
        this.fechaFin = LocalDateTime.now().plusDays(15);
        this.valor = 104000D;
        this.descripcion = "";
        this.estado = true;
        return this;
    }

    public FacturaTestDataBuilder conFacturaDiaPorDefecto(){
        this.idFactura = 3L;
        this.tipoModalidad = TipoModalidadFactura.DIA;
        this.fechaInicio = LocalDateTime.now();
        this.fechaFin = LocalDateTime.now();
        this.valor = 8000D;
        this.descripcion = "PRD09F";
        this.estado = true;
        return this;
    }

    public FacturaTestDataBuilder conFacturaDiaSinDescripcionPorDefecto(){
        this.idFactura = 3L;
        this.tipoModalidad = TipoModalidadFactura.DIA;
        this.fechaInicio = LocalDateTime.now();
        this.fechaFin = LocalDateTime.now();
        this.valor = 8000D;
        this.estado = true;
        return this;
    }

    public FacturaTestDataBuilder conFacturaDiaDescripcionVaciaPorDefecto(){
        this.idFactura = 3L;
        this.tipoModalidad = TipoModalidadFactura.DIA;
        this.fechaInicio = LocalDateTime.now();
        this.fechaFin = LocalDateTime.now();
        this.valor = 8000D;
        this.descripcion = "";
        this.estado = true;
        return this;
    }

    public FacturaTestDataBuilder conFacturaHoraSinDescripcionPorDefecto(){
        this.idFactura = 3L;
        this.tipoModalidad = TipoModalidadFactura.HORA;
        this.fechaInicio = LocalDateTime.now();
        this.fechaFin = LocalDateTime.now();
        this.valor = 8000D;
        this.estado = true;
        return this;
    }

    public FacturaTestDataBuilder conFacturaHoraDescripcionVaciaPorDefecto(){
        this.idFactura = 3L;
        this.tipoModalidad = TipoModalidadFactura.HORA;
        this.fechaInicio = LocalDateTime.now();
        this.fechaFin = LocalDateTime.now();
        this.valor = 8000D;
        this.descripcion = "";
        this.estado = true;
        return this;
    }

    public FacturaTestDataBuilder conFacturaHoraPorDefecto(){
        this.idFactura = 4L;
        this.tipoModalidad = TipoModalidadFactura.HORA;
        this.fechaInicio = LocalDateTime.now();
        this.fechaFin = LocalDateTime.now();
        this.valor = 1000D;
        this.descripcion = "TDX094";
        this.estado = true;
        return this;
    }

    public FacturaTestDataBuilder conFacturaConModalidadIncorrectaPorDefecto(){
        this.idFactura = 4L;
        this.fechaInicio = LocalDateTime.now();
        this.fechaFin = LocalDateTime.now();
        this.valor = 1000D;
        this.descripcion = "TDX094";
        this.estado = true;
        return this;
    }

    public Long getIdFactura() {
        return idFactura;
    }

    public TipoModalidadFactura getTipoModalidad() {
        return tipoModalidad;
    }

    public Long getIdPropietario() {
        return idPropietario;
    }

    public Long getIdVehiculo() {
        return idVehiculo;
    }

    public LocalDateTime getFechaInicio() {
        return fechaInicio;
    }

    public LocalDateTime getFechaFin() {
        return fechaFin;
    }

    public Double getValor() {
        return valor;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public Boolean getEstado() {
        return estado;
    }

    public Factura build(){
        return new Factura(this.idFactura, this.tipoModalidad, this.idPropietario, this.idVehiculo,
                this.descripcion, this.estado
        );
    }
}
