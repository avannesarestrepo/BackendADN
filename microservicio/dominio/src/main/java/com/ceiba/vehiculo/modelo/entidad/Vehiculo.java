package com.ceiba.vehiculo.modelo.entidad;

import com.ceiba.dominio.ValidadorArgumento;
import com.ceiba.propietario.modelo.entidad.Propietario;

public class Vehiculo {

    private static final String ID_VEHICULO_OBLIGATORIO = "El campo 'id' es obligatorio";
    private static final String PROPIETARIO_OBLIGATORIO = "El Propietario no esta diligenciado o no existe";
    private static final String PLACA_OBLIGATORIO = "El campo 'Placa' es obligatorio";
    private static final String PLACA_LONGITUD = "El campo 'Placa' debe tener una longitud de maximo 6 digitos";
    private static final String TIPO_VEHICULO_OBLIGATORIO = "El campo 'tipoVehiculo' es obligatorio";

    private Long id;
    private Propietario propietario;
    private String placa;
    private String descripcion;
    private TipoVehiculo tipoVehiculo;

    public Vehiculo(){}

    public Vehiculo(Long id, Propietario propietario, String placa, String descripcion, TipoVehiculo tipoVehiculo) {
        ValidadorArgumento.validarObligatorio(id, ID_VEHICULO_OBLIGATORIO);
        ValidadorArgumento.validarObligatorio(propietario, PROPIETARIO_OBLIGATORIO);
        ValidadorArgumento.validarObligatorio(placa, PLACA_OBLIGATORIO);
        ValidadorArgumento.validarLongitud(placa, 6, PLACA_LONGITUD);
        ValidadorArgumento.validarObligatorio(tipoVehiculo, TIPO_VEHICULO_OBLIGATORIO);

        this.id = id;
        this.propietario = propietario;
        this.placa = placa;
        this.descripcion = descripcion;
        this.tipoVehiculo = tipoVehiculo;
    }

    public Long getId() {
        return id;
    }

    public Propietario getPropietario() {
        return propietario;
    }

    public String getPlaca() {
        return placa;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public TipoVehiculo getTipoVehiculo() {
        return tipoVehiculo;
    }
}
