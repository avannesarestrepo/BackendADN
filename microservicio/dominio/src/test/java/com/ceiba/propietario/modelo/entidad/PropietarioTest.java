package com.ceiba.propietario.modelo.entidad;

import com.ceiba.BasePrueba;
import com.ceiba.dominio.excepcion.ExcepcionValorObligatorio;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class PropietarioTest {

    @Test
    void crearPropietarioExitosamente() {
        //Arrange - Act
        Propietario propietario = new PropietarioTestDataBuilder()
                .conId(1L)
                .conTipoDocumento(TipoDocumentoPropietario.CC)
                .conDocumento("1000678940")
                .conNombreCompleto("Marcela Reyes")
                .conEmail("marcelareyes@gmail.com")
                .conTelefono(1234567).build();

        //Assert
        Assertions.assertEquals(1L, propietario.getId());
        Assertions.assertEquals(TipoDocumentoPropietario.CC, propietario.getTipoDocumento());
        Assertions.assertEquals("1000678940", propietario.getDocumento());
        Assertions.assertEquals("Marcela Reyes", propietario.getNombreCompleto());
        Assertions.assertEquals("marcelareyes@gmail.com", propietario.getEmail());
        Assertions.assertEquals(1234567, propietario.getTelefono());
    }

    @Test
    void propietarioSinIdDeberiaLanzarError(){
        BasePrueba.assertThrows(() -> new PropietarioTestDataBuilder()
                .conTipoDocumento(TipoDocumentoPropietario.CC)
                .conDocumento("1000678940")
                .conNombreCompleto("Marcela Reyes")
                .conEmail("marcelareyes@gmail.com")
                .conTelefono(1234567).build(),
                ExcepcionValorObligatorio.class,
                "El campo 'id' es obligatorio"
        );
    }

    @Test
    void propietarioSinTipoDocumentoDeberiaLanzarError(){
        BasePrueba.assertThrows(() -> new PropietarioTestDataBuilder()
                        .conId(1L)
                        .conDocumento("1000678940")
                        .conNombreCompleto("Marcela Reyes")
                        .conEmail("marcelareyes@gmail.com")
                        .conTelefono(1234567).build(),
                ExcepcionValorObligatorio.class,
                "El campo 'tipo de documento' es obligatorio"
        );
    }

    @Test
    void propietarioSinDocumentoDeberiaLanzarError(){
        BasePrueba.assertThrows(() -> new PropietarioTestDataBuilder()
                        .conId(1L)
                        .conTipoDocumento(TipoDocumentoPropietario.CC)
                        .conNombreCompleto("Marcela Reyes")
                        .conEmail("marcelareyes@gmail.com")
                        .conTelefono(1234567).build(),
                ExcepcionValorObligatorio.class,
                "El campo 'documento' es obligatorio"
        );
    }

    @Test
    void propietarioSinNombreCompletoDeberiaLanzarError(){
        BasePrueba.assertThrows(() -> new PropietarioTestDataBuilder()
                        .conId(1L)
                        .conTipoDocumento(TipoDocumentoPropietario.CC)
                        .conDocumento("1000678940")
                        .conEmail("marcelareyes@gmail.com")
                        .conTelefono(1234567).build(),
                ExcepcionValorObligatorio.class,
                "El 'nombre completo' es obligatorio"
        );
    }

    @Test
    void propietarioSinEmailDeberiaLanzarError(){
        BasePrueba.assertThrows(() -> new PropietarioTestDataBuilder()
                        .conId(1L)
                        .conTipoDocumento(TipoDocumentoPropietario.CC)
                        .conDocumento("1000678940")
                        .conNombreCompleto("Marcela Reyes")
                        .conTelefono(1234567).build(),
                ExcepcionValorObligatorio.class,
                "El campo 'email' es obligatorio"
        );
    }

    @Test
    void propietarioSinTelefonoDeberiaLanzarError(){
        BasePrueba.assertThrows(() -> new PropietarioTestDataBuilder()
                        .conId(1L)
                        .conTipoDocumento(TipoDocumentoPropietario.CC)
                        .conDocumento("1000678940")
                        .conNombreCompleto("Marcela Reyes")
                        .conEmail("marcelareyes@gmail.com").build(),
                ExcepcionValorObligatorio.class,
                "El campo 'telefono' es obligatorio"
        );
    }
}