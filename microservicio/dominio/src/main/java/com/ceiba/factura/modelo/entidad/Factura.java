package com.ceiba.factura.modelo.entidad;

import com.ceiba.dominio.ValidadorArgumento;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;

public class Factura {

    private static final Double VALOR_POR_HORA = 1000D;
    private static final Double VALOR_POR_DIA = 8000D;
    private static final String TIPO_MODALIDAD_OBLIGATORIO = "El campo 'tipoModalidad' es obligatorio";
    private static final String ESTADO_OBLIGATORIO = "El campo 'estado' es obligatorio";
    private Long idFactura;
    private TipoModalidadFactura tipoModalidad;
    private Long idPropietario;
    private Long idVehiculo;
    private LocalDateTime fechaInicio;
    private LocalDateTime fechaFin;
    private Double valor;
    private String descripcion;
    private Boolean estado;

    public Factura(Long idFactura, TipoModalidadFactura tipoModalidad, Long idPropietario,
                   Long idVehiculo, String descripcion, Boolean estado) {
        this.descripcion = descripcion;

        ValidadorArgumento.validarObligatorio(tipoModalidad, TIPO_MODALIDAD_OBLIGATORIO);
        ValidadorArgumento.validarObligatorio(estado, ESTADO_OBLIGATORIO);

        this.idFactura = idFactura;
        this.tipoModalidad = tipoModalidad;
        this.idPropietario = idPropietario;
        this.idVehiculo = idVehiculo;
        this.fechaInicio = LocalDateTime.now();
        this.fechaFin = calcularFechaFin(this.fechaInicio, this.tipoModalidad);
        this.valor = generarValorTotal(this.fechaInicio, this.fechaFin, this.tipoModalidad);
        this.descripcion = descripcion;
        this.estado = estado;
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

    private LocalDateTime calcularFechaFin(LocalDateTime fechaInicioModalidad, TipoModalidadFactura tipoModalidadFactura){
        if(tipoModalidadFactura.equals(TipoModalidadFactura.MENSUAL)){
            return fechaInicioModalidad.plusDays(30);
        }else if(tipoModalidadFactura.equals(TipoModalidadFactura.QUINCENAL)){
            return fechaInicioModalidad.plusDays(15);
        }else if(tipoModalidadFactura.equals(TipoModalidadFactura.HORA)){
            return LocalDateTime.now();
        }

        return fechaInicioModalidad;
    }

    private int generarDiasDescuento(LocalDateTime fechaInicio, LocalDateTime fechaFin, TipoModalidadFactura tipoModalidadFactura) {
        long periodo = ChronoUnit.DAYS.between(fechaInicio.toLocalDate(), fechaFin.toLocalDate());
        LocalDate resultado = fechaInicio.toLocalDate();
        int addDays = 0;
        int autoIncrementalPeriodo = 0;
        while (autoIncrementalPeriodo < periodo){
            resultado = resultado.plusDays(1);
            if((tipoModalidadFactura.equals(TipoModalidadFactura.MENSUAL) &&
                    (resultado.getDayOfWeek() == DayOfWeek.SATURDAY || resultado.getDayOfWeek() == DayOfWeek.SUNDAY)) ||
                    (tipoModalidadFactura.equals(TipoModalidadFactura.QUINCENAL) && resultado.getDayOfWeek() == DayOfWeek.SUNDAY)){
                ++addDays;
            }

            ++autoIncrementalPeriodo;
        }

        return addDays;
    }

    private double generarValorTotal(LocalDateTime fechaInicio, LocalDateTime fechaFin, TipoModalidadFactura tipoModalidadFactura){
        long periodo = ChronoUnit.DAYS.between(fechaInicio.toLocalDate(), fechaFin.toLocalDate());
        int diasDescuento = generarDiasDescuento(fechaInicio, fechaFin, tipoModalidadFactura);
        double valorTotal = 0;

        if(tipoModalidadFactura.equals(TipoModalidadFactura.MENSUAL)){
            valorTotal = periodo * VALOR_POR_DIA;
            Double valorSabadosYDomingos = diasDescuento * VALOR_POR_DIA;
            valorTotal = valorTotal - valorSabadosYDomingos;
        }else if(tipoModalidadFactura.equals(TipoModalidadFactura.QUINCENAL)){
            valorTotal = periodo * VALOR_POR_DIA;
            Double valorDomingos = diasDescuento * VALOR_POR_DIA;
            valorTotal = valorTotal - valorDomingos;
        }else if(tipoModalidadFactura.equals(TipoModalidadFactura.DIA)){
            valorTotal = VALOR_POR_DIA;
        }else if(tipoModalidadFactura.equals(TipoModalidadFactura.HORA)){
            valorTotal = VALOR_POR_HORA;
        }

        return valorTotal;
    }
}
