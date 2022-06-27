package com.ceiba.propietario.modelo.entidad;

import com.ceiba.dominio.ValidadorArgumento;

public class Propietario {

    private static final String ID_OBLIGATORIO = "El campo 'id' es obligatorio";
    private static final String TIPO_DOCUMENTO_OBLIGATORIO = "El campo 'tipo de documento' es obligatorio";
    private static final String DOCUMENTO_OBLIGATORIO = "El campo 'documento' es obligatorio";
    private static final String NOMBRE_COMPLETO_OBLIGATORIO = "El 'nombre completo' es obligatorio";
    private static final String EMAIL_OBLIGATORIO = "El campo 'email' es obligatorio";
    private static final String TELEFONO_OBLIGATORIO = "El campo 'telefono' es obligatorio";

    private Long id;
    private TipoDocumentoPropietario tipoDocumento;
    private String documento;
    private String nombreCompleto;
    private String email;
    private Integer telefono;

    public Propietario() {}

    public Propietario(Long id, TipoDocumentoPropietario tipoDocumento, String documento,
                       String nombreCompleto, String email, Integer telefono) {

        ValidadorArgumento.validarObligatorio(id, ID_OBLIGATORIO);
        ValidadorArgumento.validarObligatorio(tipoDocumento, TIPO_DOCUMENTO_OBLIGATORIO);
        ValidadorArgumento.validarObligatorio(documento, DOCUMENTO_OBLIGATORIO);
        ValidadorArgumento.validarObligatorio(nombreCompleto, NOMBRE_COMPLETO_OBLIGATORIO);
        ValidadorArgumento.validarObligatorio(email, EMAIL_OBLIGATORIO);
        ValidadorArgumento.validarObligatorio(telefono, TELEFONO_OBLIGATORIO);

        this.id = id;
        this.tipoDocumento = tipoDocumento;
        this.documento = documento;
        this.nombreCompleto = nombreCompleto;
        this.email = email;
        this.telefono = telefono;
    }

    public Long getId() {
        return id;
    }

    public TipoDocumentoPropietario getTipoDocumento() {
        return tipoDocumento;
    }

    public String getDocumento() {
        return documento;
    }

    public String getNombreCompleto() {
        return nombreCompleto;
    }

    public String getEmail() {
        return email;
    }

    public Integer getTelefono() {
        return telefono;
    }
}