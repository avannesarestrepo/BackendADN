package com.ceiba.propietario.modelo.entidad;

public class PropietarioTestDataBuilder {
    private Long id;
    private TipoDocumentoPropietario tipoDocumento;
    private String documento;
    private String nombreCompleto;
    private String email;
    private Integer telefono;

    public PropietarioTestDataBuilder conPropietarioPorDefecto() {
        this.id = 1L;
        this.tipoDocumento = TipoDocumentoPropietario.CC;
        this.documento = "1000678940";
        this.nombreCompleto = "Marcela Reyes";
        this.email = "marcelareyes@gmail.com";
        this.telefono = 1234567;
        return this;
    }

    public PropietarioTestDataBuilder conId(Long id) {
        this.id = id;
        return this;
    }

    public PropietarioTestDataBuilder conTipoDocumento(TipoDocumentoPropietario tipoDocumento) {
        this.tipoDocumento = tipoDocumento;
        return this;
    }

    public PropietarioTestDataBuilder conDocumento(String documento) {
        this.documento = documento;
        return this;
    }

    public PropietarioTestDataBuilder conNombreCompleto(String nombreCompleto) {
        this.nombreCompleto = nombreCompleto;
        return this;
    }

    public PropietarioTestDataBuilder conEmail(String email) {
        this.email = email;
        return this;
    }

    public PropietarioTestDataBuilder conTelefono(Integer telefono) {
        this.telefono = telefono;
        return this;
    }

    public Propietario reconstruir() {
        return Propietario.reconstruir(id, tipoDocumento, documento, nombreCompleto, email, telefono);
    }

    public Propietario build() {
        return new Propietario(this.id, this.tipoDocumento, this.documento, this.nombreCompleto, this.email, this.telefono);
    }
}
