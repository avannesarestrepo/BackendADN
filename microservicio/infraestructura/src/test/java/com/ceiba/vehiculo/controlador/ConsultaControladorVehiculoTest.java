package com.ceiba.vehiculo.controlador;

import com.ceiba.ApplicationMock;
import com.ceiba.vehiculo.comando.ComandoVehiculo;
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
@WebMvcTest(ConsultaControladorVehiculo.class)
@ContextConfiguration(classes = ApplicationMock.class)
@DirtiesContext(classMode = DirtiesContext.ClassMode.AFTER_EACH_TEST_METHOD)
public class ConsultaControladorVehiculoTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @Test
    void consultarVehiculoPorPlaca() throws Exception {
        ComandoVehiculo comandoVehiculo = new ComandoVehiculoTestDataBuilder()
                .vehiculoExistentePorDefecto().build();

        mockMvc.perform(get("/vehiculo")
                .param("placa", "XOR435")
                .contentType(MediaType.APPLICATION_JSON))
        .andExpect(status().is2xxSuccessful())
        .andExpect(jsonPath("$.id", is(comandoVehiculo.getId().intValue())))
        .andExpect(jsonPath("$.placa", is(comandoVehiculo.getPlaca())))
        .andExpect(jsonPath("$.descripcion", is(comandoVehiculo.getDescripcion())))
        .andExpect(jsonPath("$.tipoVehiculo", is(String.valueOf(comandoVehiculo.getTipoVehiculo()))))
        .andReturn();
    }

    @Test
    void consultarVehiculoPorId() throws Exception {
        ComandoVehiculo comandoVehiculo = new ComandoVehiculoTestDataBuilder()
                .vehiculoExistentePorDefecto().build();

        mockMvc.perform(get("/vehiculo/2")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().is2xxSuccessful())
                .andExpect(jsonPath("$.id", is(comandoVehiculo.getId().intValue())))
                .andExpect(jsonPath("$.placa", is(comandoVehiculo.getPlaca())))
                .andExpect(jsonPath("$.descripcion", is(comandoVehiculo.getDescripcion())))
                .andExpect(jsonPath("$.tipoVehiculo", is(String.valueOf(comandoVehiculo.getTipoVehiculo()))))
                .andReturn();
    }
}
