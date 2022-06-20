package com.ceiba.propietario.modelo.entidad;

import com.ceiba.BasePrueba;
import com.ceiba.dominio.excepcion.ExcepcionValorObligatorio;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class PropietarioTest {

    @Test
    void constructorYGetTest() {
        //Arrange - Act
        Propietario propietario = new Propietario(
                1L,
                TipoDocumentoPropietario.CC,
                "1000678940",
                "Marcela Reyes",
                "marcelareyes@gmail.com",
                1234567);

        //Assert
        Assertions.assertEquals(1L, propietario.getId());
        Assertions.assertEquals(TipoDocumentoPropietario.CC, propietario.getTipoDocumento());
        Assertions.assertEquals("1000678940", propietario.getDocumento());
        Assertions.assertEquals("Marcela Reyes", propietario.getNombreCompleto());
        Assertions.assertEquals("marcelareyes@gmail.com", propietario.getEmail());
        Assertions.assertEquals(1234567, propietario.getTelefono());
    }

    @Test
    void crearPropietarioExitosamente() {
        //Arrange - Act
        Propietario propietario = new PropietarioTestDataBuilder()
                .conId(1L)
                .conTipoDocumento(TipoDocumentoPropietario.CC)
                .conDocumento("1000678940")
                .conNombreCompleto("Marcela Reyes")
                .conEmail("marcelareyes@gmail.com")
                .conTelefono(1234567).reconstruir();

        //Assert
        Assertions.assertEquals(1L, propietario.getId());
        Assertions.assertEquals(TipoDocumentoPropietario.CC, propietario.getTipoDocumento());
        Assertions.assertEquals("1000678940", propietario.getDocumento());
        Assertions.assertEquals("Marcela Reyes", propietario.getNombreCompleto());
        Assertions.assertEquals("marcelareyes@gmail.com", propietario.getEmail());
        Assertions.assertEquals(1234567, propietario.getTelefono());
    }

    @Test
    void reconstruirPropietarioSinIdDeberiaLanzarError(){
        BasePrueba.assertThrows(() -> new PropietarioTestDataBuilder()
                .conTipoDocumento(TipoDocumentoPropietario.CC)
                .conDocumento("1000678940")
                .conNombreCompleto("Marcela Reyes")
                .conEmail("marcelareyes@gmail.com")
                .conTelefono(1234567).reconstruir(),
                ExcepcionValorObligatorio.class,
                "El campo 'id' es obligatorio"
        );
    }

    @Test
    void reconstruirPropietarioSinTipoDocumentoDeberiaLanzarError(){
        BasePrueba.assertThrows(() -> new PropietarioTestDataBuilder()
                        .conId(1L)
                        .conDocumento("1000678940")
                        .conNombreCompleto("Marcela Reyes")
                        .conEmail("marcelareyes@gmail.com")
                        .conTelefono(1234567).reconstruir(),
                ExcepcionValorObligatorio.class,
                "El campo 'tipo de documento' es obligatorio"
        );
    }

    @Test
    void reconstruirPropietarioSinDocumentoDeberiaLanzarError(){
        BasePrueba.assertThrows(() -> new PropietarioTestDataBuilder()
                        .conId(1L)
                        .conTipoDocumento(TipoDocumentoPropietario.CC)
                        .conNombreCompleto("Marcela Reyes")
                        .conEmail("marcelareyes@gmail.com")
                        .conTelefono(1234567).reconstruir(),
                ExcepcionValorObligatorio.class,
                "El campo 'documento' es obligatorio"
        );
    }

    @Test
    void reconstruirPropietarioSinNombreCompletoDeberiaLanzarError(){
        BasePrueba.assertThrows(() -> new PropietarioTestDataBuilder()
                        .conId(1L)
                        .conTipoDocumento(TipoDocumentoPropietario.CC)
                        .conDocumento("1000678940")
                        .conEmail("marcelareyes@gmail.com")
                        .conTelefono(1234567).reconstruir(),
                ExcepcionValorObligatorio.class,
                "El 'nombre completo' es obligatorio"
        );
    }

    @Test
    void reconstruirPropietarioSinEmailDeberiaLanzarError(){
        BasePrueba.assertThrows(() -> new PropietarioTestDataBuilder()
                        .conId(1L)
                        .conTipoDocumento(TipoDocumentoPropietario.CC)
                        .conDocumento("1000678940")
                        .conNombreCompleto("Marcela Reyes")
                        .conTelefono(1234567).reconstruir(),
                ExcepcionValorObligatorio.class,
                "El campo 'email' es obligatorio"
        );
    }

    @Test
    void reconstruirPropietarioSinTelefonoDeberiaLanzarError(){
        BasePrueba.assertThrows(() -> new PropietarioTestDataBuilder()
                        .conId(1L)
                        .conTipoDocumento(TipoDocumentoPropietario.CC)
                        .conDocumento("1000678940")
                        .conNombreCompleto("Marcela Reyes")
                        .conEmail("marcelareyes@gmail.com").reconstruir(),
                ExcepcionValorObligatorio.class,
                "El campo 'telefono' es obligatorio"
        );
    }
}