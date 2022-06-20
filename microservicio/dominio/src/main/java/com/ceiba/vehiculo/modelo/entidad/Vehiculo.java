package com.ceiba.vehiculo.modelo.entidad;

import com.ceiba.dominio.ValidadorArgumento;

public class Vehiculo {

    private static final String ID_VEHICULO_OBLIGATORIO = "El campo 'idVehiculo' es obligatorio";
    private static final String ID_PROPIETARIO_OBLIGATORIO = "El campo 'idPropiedad' es obligatorio";
    private static final String PLACA_OBLIGATORIO = "El campo 'Placa' es obligatorio";
    private static final String PLACA_LONGITUD = "El campo 'Placa' debe tener una longitud de maximo 6 digitos";
    private static final String TIPO_VEHICULO_OBLIGATORIO = "El campo 'tipoVehiculo' es obligatorio";
    private Long idVehiculo;
    private Long idPropietario;
    private String placa;
    private String descripcion;
    private TipoVehiculo tipoVehiculo;

    public Vehiculo(Long idVehiculo, Long idPropietario, String placa, String descripcion,
                    TipoVehiculo tipoVehiculo) {

        ValidadorArgumento.validarObligatorio(idVehiculo, ID_VEHICULO_OBLIGATORIO);
        ValidadorArgumento.validarObligatorio(idPropietario, ID_PROPIETARIO_OBLIGATORIO);
        ValidadorArgumento.validarObligatorio(placa, PLACA_OBLIGATORIO);
        ValidadorArgumento.validarLongitud(placa, 6, PLACA_LONGITUD);
        ValidadorArgumento.validarObligatorio(tipoVehiculo, TIPO_VEHICULO_OBLIGATORIO);

        this.idVehiculo = idVehiculo;
        this.idPropietario = idPropietario;
        this.placa = placa;
        this.descripcion = descripcion;
        this.tipoVehiculo = tipoVehiculo;
    }

    public Long getIdVehiculo() {
        return idVehiculo;
    }

    public Long getIdPropietario() {
        return idPropietario;
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
