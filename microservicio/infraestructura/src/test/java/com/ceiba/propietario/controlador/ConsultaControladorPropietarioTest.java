package com.ceiba.propietario.controlador;

import com.ceiba.ApplicationMock;
import com.ceiba.propietario.comando.ComandoPropietario;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.http.MediaType;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;

import static org.hamcrest.Matchers.is;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(SpringExtension.class)
@WebMvcTest(ConsultaControladorPropietario.class)
@ContextConfiguration(classes = ApplicationMock.class)
@DirtiesContext(classMode = DirtiesContext.ClassMode.AFTER_EACH_TEST_METHOD)
public class ConsultaControladorPropietarioTest {
    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @Test
    void consultarPropietarioPorIdExitosa() throws Exception {
        ComandoPropietario comandoPropietarioTestDataBuilder = new ComandoPropietarioTestDataBuilder().propietarioExistentePorDefecto().build();

        mockMvc.perform(get("/propietario")
                .param("id", "2")
                .contentType(MediaType.APPLICATION_JSON))
        .andExpect(status().is2xxSuccessful())
        .andExpect(jsonPath("$.id", is(comandoPropietarioTestDataBuilder.getId().intValue())))
        .andExpect(jsonPath("$.tipoDocumento", is(comandoPropietarioTestDataBuilder.getTipoDocumento().toString())))
        .andExpect(jsonPath("$.documento", is(comandoPropietarioTestDataBuilder.getDocumento())))
        .andExpect(jsonPath("$.nombreCompleto", is(comandoPropietarioTestDataBuilder.getNombreCompleto())))
        .andExpect(jsonPath("$.email", is(comandoPropietarioTestDataBuilder.getEmail())))
        .andExpect(jsonPath("$.telefono", is(comandoPropietarioTestDataBuilder.getTelefono())))
        .andReturn();
    }

    @Test
    void consultarPropietarioPorDocumentoExitosa() throws Exception {
        ComandoPropietario comandoPropietarioTestDataBuilder = new ComandoPropietarioTestDataBuilder().propietarioExistentePorDefecto().build();

        mockMvc.perform(get("/propietario")
                .param("documento", "54321")
                .contentType(MediaType.APPLICATION_JSON))
        .andExpect(status().is2xxSuccessful())
        .andExpect(jsonPath("$.id", is(comandoPropietarioTestDataBuilder.getId().intValue())))
        .andExpect(jsonPath("$.tipoDocumento", is(comandoPropietarioTestDataBuilder.getTipoDocumento().toString())))
        .andExpect(jsonPath("$.documento", is(comandoPropietarioTestDataBuilder.getDocumento())))
        .andExpect(jsonPath("$.nombreCompleto", is(comandoPropietarioTestDataBuilder.getNombreCompleto())))
        .andExpect(jsonPath("$.email", is(comandoPropietarioTestDataBuilder.getEmail())))
        .andExpect(jsonPath("$.telefono", is(comandoPropietarioTestDataBuilder.getTelefono())))
        .andReturn();
    }

    @Test
    void existePropietarioPorIdExitosa() throws Exception {
        mockMvc.perform(get("/propietario/2")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().is2xxSuccessful())
                .andReturn();
    }

    @Test
    void existePropietarioPorDocumentoExitosa() throws Exception {
        mockMvc.perform(get("/propietario")
                        .param("validarDocumento", "54321")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().is2xxSuccessful())
                .andReturn();
    }
}
