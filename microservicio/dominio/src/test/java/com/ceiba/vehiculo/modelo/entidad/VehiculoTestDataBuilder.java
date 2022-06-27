package com.ceiba.vehiculo.modelo.entidad;

import com.ceiba.propietario.modelo.entidad.Propietario;
import com.ceiba.propietario.modelo.entidad.PropietarioTestDataBuilder;

public class VehiculoTestDataBuilder {
    private Long id;
    private Propietario propietario;
    private String placa;
    private String descripcion;
    private TipoVehiculo tipoVehiculo;

    public VehiculoTestDataBuilder conVehiculoPorDefecto() {
        this.id = 1L;
        this.propietario = new PropietarioTestDataBuilder().conPropietarioPorDefecto().build();
        this.placa = "RTE024";
        this.descripcion = "Carro rojo marca Renault";
        this.tipoVehiculo = TipoVehiculo.CARRO;
        return this;
    }

    public VehiculoTestDataBuilder conId(Long id){
        this.id = id;
        return this;
    }

    public VehiculoTestDataBuilder conPropietario(Propietario propietario){
        this.propietario = propietario;
        return this;
    }

    public VehiculoTestDataBuilder conPlaca(String placa){
        this.placa = placa;
        return this;
    }

    public VehiculoTestDataBuilder conDescripcion(String descripcion){
        this.descripcion = descripcion;
        return this;
    }

    public VehiculoTestDataBuilder conTipoVehiculo(TipoVehiculo tipoVehiculo){
        this.tipoVehiculo = tipoVehiculo;
        return this;
    }

    public Vehiculo build(){
        return new Vehiculo(this.id, this.propietario, this.placa, this.descripcion, this.tipoVehiculo);
    }
}
