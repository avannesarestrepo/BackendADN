package com.ceiba.vehiculo.controlador;

import com.ceiba.propietario.modelo.entidad.Propietario;
import com.ceiba.propietario.modelo.entidad.TipoDocumentoPropietario;
import com.ceiba.vehiculo.comando.ComandoVehiculo;
import com.ceiba.vehiculo.modelo.entidad.TipoVehiculo;

public class ComandoVehiculoTestDataBuilder {
    private Long id;
    private Propietario propietario;
    private String placa;
    private String descripcion;
    private TipoVehiculo tipoVehiculo;

    public ComandoVehiculoTestDataBuilder crearPorDefectoVehiculo(){
        this.id = 5L;
        this.propietario = new Propietario(2L, TipoDocumentoPropietario.CC, "54321", "Angie Vannesa", "ava@gmail.com", 87565);
        this.placa = "SDF567";
        this.descripcion = "Carro Gris con negro Renault";
        this.tipoVehiculo = TipoVehiculo.CARRO;
        return this;
    }

    public ComandoVehiculoTestDataBuilder vehiculoExistentePorDefecto(){
        this.id = 2L;
        this.propietario = new Propietario(2L, TipoDocumentoPropietario.CC, "54321", "Angie Vannesa", "ava@gmail.com", 87565);
        this.placa = "XOR435";
        this.descripcion = "Carro Gris Renault";
        this.tipoVehiculo = TipoVehiculo.CARRO;
        return this;
    }

    public ComandoVehiculo build(){
        return new ComandoVehiculo(id, propietario, placa, descripcion, tipoVehiculo);
    }
}
