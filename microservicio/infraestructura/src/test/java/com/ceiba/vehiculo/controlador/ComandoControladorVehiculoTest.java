package com.ceiba.vehiculo.controlador;

import com.ceiba.ApplicationMock;
import com.ceiba.vehiculo.comando.ComandoVehiculo;
import com.ceiba.vehiculo.modelo.entidad.Vehiculo;
import com.ceiba.vehiculo.puerto.repositorio.RepositorioVehiculo;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.http.MediaType;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(SpringExtension.class)
@WebMvcTest(ComandoControladorVehiculo.class)
@ContextConfiguration(classes = ApplicationMock.class)
@DirtiesContext(classMode = DirtiesContext.ClassMode.AFTER_EACH_TEST_METHOD)
public class ComandoControladorVehiculoTest {

    @Autowired
    private ObjectMapper objectMapper;

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    RepositorioVehiculo repositorioVehiculo;

    @Test
    void crearVehiculoExitosamente() throws Exception {
        ComandoVehiculo comandoVehiculoTestDataBuilder = new ComandoVehiculoTestDataBuilder()
                .crearPorDefectoVehiculo().build();

        MvcResult resultado = mockMvc.perform(post("/vehiculo")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(comandoVehiculoTestDataBuilder)))
                .andExpect(status().is2xxSuccessful()).andReturn();

        String jsonResultado = resultado.getResponse().getContentAsString();
        RespuestaVehiculo respuestaVehiculo = objectMapper.readValue(jsonResultado, RespuestaVehiculo.class);

        Vehiculo vehiculo = repositorioVehiculo.obtenerVehiculoPorId(respuestaVehiculo.getValor());

        Assertions.assertEquals(5L, vehiculo.getId());
        Assertions.assertEquals("SDF567", vehiculo.getPlaca());
    }

    @Test
    void actualizarVehiculoExitosamente() throws Exception {
        ComandoVehiculo comandoVehiculo = new ComandoVehiculoTestDataBuilder()
                .vehiculoExistentePorDefecto().build();
        comandoVehiculo.setPlaca("XOR437");

        mockMvc.perform(put("/vehiculo/2")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(comandoVehiculo)))
                .andExpect(status().is2xxSuccessful()).andReturn();

        Vehiculo vehiculo = repositorioVehiculo.obtenerVehiculoPorId(comandoVehiculo.getId());

        Assertions.assertEquals(2L, vehiculo.getId());
        Assertions.assertEquals("XOR437", vehiculo.getPlaca());
    }

    @Test
    void eliminarVehiculoExitosamente() throws Exception {
        mockMvc.perform(delete("/vehiculo/")
                        .param("placa", "XXX000")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().is2xxSuccessful()).andReturn();
    }
}
