package com.ceiba.factura.modelo.entidad;

import com.ceiba.dominio.ValidadorArgumento;
import com.ceiba.dominio.excepcion.ExcepcionSinDatos;
import com.ceiba.vehiculo.modelo.entidad.Vehiculo;

import java.math.BigDecimal;
import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;

public class Factura {

    private static final BigDecimal VALOR_POR_DIA = BigDecimal.valueOf(8000);
    private static final BigDecimal VALOR_POR_HORA = BigDecimal.valueOf(1000);

    private static final String ID_OBLIGATORIO = "El campo 'id' es obligatorio";
    private static final String TIPO_MODALIDAD_OBLIGATORIO = "El campo 'tipoModalidad' es obligatorio";
    private static final String VEHICULO_OBLIGATORIO = "El Vehiculo es obligatorio";
    private static final String FECHA_INICIO_OBLIGATORIO = "El campo 'fechaInicio' es obligatorio";
    private static final String ESTADO_OBLIGATORIO = "El campo 'estado' es obligatorio";
    private static final String PLACA_OBLIGATORIO = "El campo 'placa' para esta modalidad es obligatorio";

    private Long id;
    private TipoModalidad tipoModalidad;
    private Vehiculo vehiculo;
    private LocalDateTime fechaInicio;
    private LocalDateTime fechaFin;
    private BigDecimal valor;
    private String placa;
    private EstadoFactura estadoFactura;

    private Factura(TipoModalidad tipoModalidad, Vehiculo vehiculo, LocalDateTime fechaInicio,
                    String placa){
        this.tipoModalidad = tipoModalidad;
        this.vehiculo = vehiculo;
        this.fechaInicio = fechaInicio;
        this.estadoFactura = obtenerEstado();
        this.fechaFin = calcularFechaFin();
        this.valor = calcularValorTotal();
        this.placa = placa;
        esVehiculoObligatorio();
        esPlacaObligatorio();
    }

    public Factura(Long id, TipoModalidad tipoModalidad, Vehiculo vehiculo, LocalDateTime fechaInicio, LocalDateTime fechaFin, BigDecimal valor, String placa, EstadoFactura estadoFactura) {
        this.id = id;
        this.tipoModalidad = tipoModalidad;
        this.vehiculo = vehiculo;
        this.fechaInicio = fechaInicio;
        this.estadoFactura = estadoFactura;
        this.fechaFin = calcularFechaFin();
        this.valor = calcularValorTotal();
        this.placa = placa;
        esVehiculoObligatorio();
        esPlacaObligatorio();
    }

    public static  Factura crear(SolicitarFactura solicitarFactura){
        ValidadorArgumento.validarObligatorio(solicitarFactura.getTipoModalidad(), TIPO_MODALIDAD_OBLIGATORIO);
        ValidadorArgumento.validarObligatorio(solicitarFactura.getFechaInicio(), FECHA_INICIO_OBLIGATORIO);
        return new Factura(solicitarFactura.getTipoModalidad(), solicitarFactura.getVehiculo(),
                solicitarFactura.getFechaInicio(), solicitarFactura.getPlaca());
    }

    public static Factura reconstruir(Long id, TipoModalidad tipoModalidad, Vehiculo vehiculo, LocalDateTime fechaInicio,
                                      LocalDateTime fechaFin, BigDecimal valor, String placa, EstadoFactura estadoFactura){
        ValidadorArgumento.validarObligatorio(id, ID_OBLIGATORIO);
        ValidadorArgumento.validarObligatorio(tipoModalidad, TIPO_MODALIDAD_OBLIGATORIO);
        ValidadorArgumento.validarObligatorio(fechaInicio, FECHA_INICIO_OBLIGATORIO);
        ValidadorArgumento.validarObligatorio(estadoFactura, ESTADO_OBLIGATORIO);
        return new Factura(id,tipoModalidad, vehiculo, fechaInicio, fechaFin, valor, placa, estadoFactura);
    }

    public Boolean esMensual(){
        return this.tipoModalidad.equals(TipoModalidad.MENSUAL);
    }

    public Boolean esQuincenal(){
        return this.tipoModalidad.equals(TipoModalidad.QUINCENAL);
    }

    public Boolean esDia(){
        return this.tipoModalidad.equals(TipoModalidad.DIA);
    }

    public Boolean esHora(){
        return this.tipoModalidad.equals(TipoModalidad.HORA);
    }

    public Boolean esFacturaConfirmada(){
        return this.estadoFactura.equals(EstadoFactura.CONFIRMADA);
    }

    public Boolean esFacturaPendiente() {
        return this.estadoFactura.equals(EstadoFactura.PENDIENTE);
    }

    public Boolean esSabadoODomingo(LocalDate fecha){
        return (fecha.getDayOfWeek() == DayOfWeek.SATURDAY || fecha.getDayOfWeek() == DayOfWeek.SUNDAY);
    }

    public Boolean esDomingo(LocalDate fecha){
        return fecha.getDayOfWeek() == DayOfWeek.SUNDAY;
    }

    public Long obtenerIntervalosDiasFechaIniciaAFechaFin(){
        return ChronoUnit.DAYS.between(fechaInicio.toLocalDate(), fechaFin.toLocalDate());
    }

    public Long obtenerIntervalosHorasFechaIniciaAFechaFin(){
        return ChronoUnit.HOURS.between(fechaInicio, fechaFin);
    }

    private EstadoFactura obtenerEstado() {
        if(esHora())
            return EstadoFactura.PENDIENTE;

        return EstadoFactura.CONFIRMADA;
    }

    private LocalDateTime calcularFechaFin(){
        if(esMensual()){
            return this.fechaInicio.plusDays(30);
        }else if(esQuincenal()){
            return this.fechaInicio.plusDays(15);
        }else if(esHora() && esFacturaConfirmada()){
            return LocalDateTime.now();
        }

        return this.fechaInicio;
    }

    private BigDecimal calcularValorTotal(){
        if(esHora() && esFacturaConfirmada()){
            return VALOR_POR_HORA.multiply(BigDecimal.valueOf(obtenerIntervalosHorasFechaIniciaAFechaFin()));
        }else if(esDia()){
            return VALOR_POR_DIA;
        }

        BigDecimal valorTotal = VALOR_POR_DIA.multiply(BigDecimal.valueOf(obtenerIntervalosDiasFechaIniciaAFechaFin()));
        BigDecimal descuento = VALOR_POR_DIA.multiply(BigDecimal.valueOf(calcularDiasDescuentoPorModalidad()));

        return valorTotal.subtract(descuento);
    }

    public Integer calcularDiasDescuentoPorModalidad(){
        LocalDate fecha = this.fechaInicio.toLocalDate();
        int diaDescuento = 0;
        int autoIncrementoDias = 0;

        while(autoIncrementoDias < obtenerIntervalosDiasFechaIniciaAFechaFin()){
            if((esMensual() && esSabadoODomingo(fecha)) || (esQuincenal() && esDomingo(fecha))){
                ++diaDescuento;
            }
            ++autoIncrementoDias;
            fecha = fecha.plusDays(1);
        }

        return diaDescuento;
    }

    public void esVehiculoObligatorio(){
        if((esMensual() || esQuincenal()) && this.vehiculo == null){
            throw new ExcepcionSinDatos(VEHICULO_OBLIGATORIO);
        }
    }

    public void esPlacaObligatorio(){
        if((esDia() || esHora()) && this.placa.isEmpty()){
            throw new ExcepcionSinDatos(PLACA_OBLIGATORIO);
        }
    }

    public Long getId() {
        return id;
    }

    public TipoModalidad getTipoModalidad() {
        return tipoModalidad;
    }

    public Vehiculo getVehiculo() {
        return vehiculo;
    }

    public LocalDateTime getFechaInicio() {
        return fechaInicio;
    }

    public LocalDateTime getFechaFin() {
        return fechaFin;
    }
    public BigDecimal getValor() {
        return valor;
    }

    public String getPlaca() {
        return placa;
    }

    public EstadoFactura getEstadoFactura() {
        return estadoFactura;
    }
}
