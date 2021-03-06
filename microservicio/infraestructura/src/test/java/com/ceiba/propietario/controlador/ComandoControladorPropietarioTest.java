package com.ceiba.propietario.controlador;

import com.ceiba.ApplicationMock;
import com.ceiba.propietario.comando.ComandoPropietario;
import com.ceiba.propietario.modelo.dto.PropietarioDTO;
import com.ceiba.propietario.puerto.dao.DaoPropietario;
import com.ceiba.propietario.puerto.repositorio.RepositorioPropietario;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.http.MediaType;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;

import static org.hamcrest.Matchers.is;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(SpringExtension.class)
@WebMvcTest(ComandoControladorPropietario.class)
@ContextConfiguration(classes = ApplicationMock.class)
@DirtiesContext(classMode = DirtiesContext.ClassMode.AFTER_EACH_TEST_METHOD)
public class ComandoControladorPropietarioTest {

    private static final String PROPIETARIO_NO_EXISTE = "El propietario no existe en el sistema";
    private static final String PROPIETARIO_EXISTENTE = "El propietario ya existe";

    @Autowired
    private ObjectMapper objectMapper;

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private RepositorioPropietario repositorioPropietario;

    @Autowired
    private DaoPropietario daoPropietario;

    @Test
    void guardarPropietarioExitosamente() throws Exception {
        ComandoPropietario comandoPropietarioTestDataBuilder = new ComandoPropietarioTestDataBuilder().crearPorDefectoPropietario().build();

        MvcResult resultado = mockMvc.perform(post("/propietario")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(comandoPropietarioTestDataBuilder)))
                .andExpect(status().is2xxSuccessful()).andReturn();

        String jsonResultado = resultado.getResponse().getContentAsString();
        RespuestaPropietario respuesta = objectMapper.readValue(jsonResultado, RespuestaPropietario.class);

        PropietarioDTO dtoPropietario = daoPropietario.obtenerPorId(respuesta.getValor());

        Assertions.assertEquals("12345", dtoPropietario.getDocumento());
        Assertions.assertEquals("Angie Restrepo", dtoPropietario.getNombreCompleto());
    }

    @Test
    void guardarPropietarioDuplicadoDebeFallar() throws Exception {
        ComandoPropietario comandoPropietarioTestDataBuilder = new ComandoPropietarioTestDataBuilder().propietarioExistentePorDefecto().build();

        MvcResult resultado = mockMvc.perform(post("/propietario")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(comandoPropietarioTestDataBuilder)))
                .andExpect(status().is4xxClientError())
                .andExpect(jsonPath("$.nombreExcepcion").exists())
                .andExpect(jsonPath("$.mensaje").exists())
                .andExpect(jsonPath("$.mensaje", is(PROPIETARIO_EXISTENTE)))
                .andReturn();
    }

    @Test
    void actualizarPropietarioExitosamente() throws Exception {
        ComandoPropietario comandoPropietarioTestDataBuilder = new ComandoPropietarioTestDataBuilder().propietarioExistentePorDefecto().build();
        comandoPropietarioTestDataBuilder.setNombreCompleto("Angie V Restrepo");

        mockMvc.perform(put("/propietario/2")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(comandoPropietarioTestDataBuilder)))
                .andExpect(status().is2xxSuccessful()).andReturn();

        PropietarioDTO dtoPropietario = daoPropietario.obtenerPorId(comandoPropietarioTestDataBuilder.getId());

        Assertions.assertEquals("54321", dtoPropietario.getDocumento());
        Assertions.assertEquals("Angie V Restrepo", dtoPropietario.getNombreCompleto());
    }

    @Test
    void actualizarPropietarioNoExistenteDebeFallar() throws Exception {
        ComandoPropietario comandoPropietarioTestDataBuilder = new ComandoPropietarioTestDataBuilder().crearPorDefectoPropietario().build();
        comandoPropietarioTestDataBuilder.setNombreCompleto("Angie V Restrepo");

        MvcResult resultado = mockMvc.perform(put("/propietario/5")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(comandoPropietarioTestDataBuilder)))
                .andExpect(status().is4xxClientError())
                .andExpect(jsonPath("$.nombreExcepcion").exists())
                .andExpect(jsonPath("$.mensaje").exists())
                .andExpect(jsonPath("$.mensaje", is(PROPIETARIO_NO_EXISTE)))
                .andReturn();
    }

    @Test
    void eliminarPropietarioExitosamente() throws Exception {
        mockMvc.perform(delete("/propietario/2")
                .contentType(MediaType.APPLICATION_JSON))
        .andExpect(status().is2xxSuccessful()).andReturn();
    }

    @Test
    void eliminarPropietarioNoExistenteDebeFallar() throws Exception {
        mockMvc.perform(delete("/propietario/5")
                .contentType(MediaType.APPLICATION_JSON))
        .andExpect(status().is4xxClientError())
        .andExpect(jsonPath("$.nombreExcepcion").exists())
        .andExpect(jsonPath("$.mensaje").exists())
        .andExpect(jsonPath("$.mensaje", is(PROPIETARIO_NO_EXISTE)))
        .andReturn();
    }
}
