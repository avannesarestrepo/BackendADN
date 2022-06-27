package com.ceiba.propietario.comando;

import com.ceiba.propietario.modelo.entidad.TipoDocumentoPropietario;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ComandoPropietario {
    private Long id;
    private TipoDocumentoPropietario tipoDocumento;
    private String documento;
    private String nombreCompleto;
    private String email;
    private Integer telefono;
}
