package com.ceiba.propietario.controlador;

import com.ceiba.propietario.comando.ComandoPropietario;
import com.ceiba.propietario.modelo.entidad.TipoDocumentoPropietario;

public class ComandoPropietarioTestDataBuilder {

    private Long id;
    private TipoDocumentoPropietario tipoDocumento;
    private String documento;
    private String nombreCompleto;
    private String email;
    private Integer telefono;

    public ComandoPropietarioTestDataBuilder crearPorDefectoPropietario(){
        this.id = 1L;
        this.tipoDocumento = TipoDocumentoPropietario.CC;
        this.documento = "12345";
        this.nombreCompleto = "Angie Restrepo";
        this.email = "ava@gmail.com";
        this.telefono = 564749;
        return this;
    }

    public ComandoPropietarioTestDataBuilder propietarioExistentePorDefecto(){
        this.id = 2L;
        this.tipoDocumento = TipoDocumentoPropietario.CC;
        this.documento = "54321";
        this.nombreCompleto = "Angie Vannesa";
        this.email = "ava@gmail.com";
        this.telefono = 87565;
        return this;
    }

    public ComandoPropietario build(){
        return new ComandoPropietario(id, tipoDocumento, documento, nombreCompleto,
                                      email, telefono);
    }


}
