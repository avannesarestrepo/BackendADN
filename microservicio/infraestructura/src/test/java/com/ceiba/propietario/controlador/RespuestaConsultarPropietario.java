package com.ceiba.propietario.controlador;

import com.ceiba.propietario.modelo.entidad.TipoDocumentoPropietario;

public class RespuestaConsultarPropietario {
        private Long id;
        private TipoDocumentoPropietario tipoDocumento;
        private String documento;
        private String nombreCompleto;
        private String email;
        private Integer telefono;

        public RespuestaConsultarPropietario(Long id, TipoDocumentoPropietario tipoDocumento, String documento,
                                             String nombreCompleto, String email, Integer telefono) {
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
