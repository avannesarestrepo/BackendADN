package com.ceiba.propietario.modelo.dto;

import com.ceiba.propietario.modelo.entidad.TipoDocumentoPropietario;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class PropietarioDTO {
    private Long id;
    private TipoDocumentoPropietario tipoDocumento;
    private String documento;
    private String nombreCompleto;
    private String email;
    private Integer telefono;
}
