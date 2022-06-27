package com.ceiba.factura.controlador;

import com.ceiba.factura.comando.ComandoModificarFactura;
import com.ceiba.factura.comando.ComandoSolicitarFactura;
import com.ceiba.factura.modelo.entidad.EstadoFactura;
import com.ceiba.factura.modelo.entidad.TipoModalidad;
import com.ceiba.propietario.modelo.entidad.Propietario;
import com.ceiba.propietario.modelo.entidad.TipoDocumentoPropietario;
import com.ceiba.vehiculo.modelo.entidad.TipoVehiculo;
import com.ceiba.vehiculo.modelo.entidad.Vehiculo;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.time.LocalDateTime;

public class ComandoFacturaTestDataBuilder {
    private Long id;
    private TipoModalidad tipoModalidad;
    private Vehiculo vehiculo;
    private String fechaInicio;
    private LocalDateTime fechaFin;
    private BigDecimal valor;
    private String placa;
    private EstadoFactura estadoFactura;

    public ComandoFacturaTestDataBuilder crearPorDefectoFactura(){
        this.tipoModalidad = TipoModalidad.MENSUAL;
        this.vehiculo = new Vehiculo(2L, new Propietario(2L, TipoDocumentoPropietario.CC, "54321", "Angie Vannesa", "ava@gmail.com", 87565), "XOR435", "Carro Gris Renault", TipoVehiculo.CARRO);
        this.fechaInicio = "2022-06-25 21:51:05.060967600";
        this.placa = "";
        this.estadoFactura = EstadoFactura.CONFIRMADA;
        return this;
    }

    public ComandoFacturaTestDataBuilder facturaExistentePorDefecto(){
        this.id = 3L;
        this.tipoModalidad = TipoModalidad.MENSUAL;
        this.vehiculo = new Vehiculo(2L, new Propietario(2L, TipoDocumentoPropietario.CC, "54321", "Angie Vannesa", "ava@gmail.com", 87565), "XOR435", "Carro Gris Renault", TipoVehiculo.CARRO);
        this.fechaInicio = "2022-06-16 21:51:05.060967600";
        this.placa ="";
        this.estadoFactura = EstadoFactura.CONFIRMADA;
        return this;
    }

    public ComandoSolicitarFactura build(){
        return new ComandoSolicitarFactura(tipoModalidad, vehiculo, fechaInicio, placa);
    }

    public ComandoModificarFactura buildActualizar(){
        return new ComandoModificarFactura(id, tipoModalidad, vehiculo, fechaInicio, placa, estadoFactura);
    }
}
