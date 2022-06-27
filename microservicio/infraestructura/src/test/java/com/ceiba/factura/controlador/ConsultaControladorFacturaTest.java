package com.ceiba.factura.controlador;

import com.ceiba.ApplicationMock;
import com.ceiba.factura.comando.ComandoModificarFactura;
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
@WebMvcTest(ConsultaControladorFactura.class)
@ContextConfiguration(classes = ApplicationMock.class)
@DirtiesContext(classMode = DirtiesContext.ClassMode.AFTER_EACH_TEST_METHOD)
public class ConsultaControladorFacturaTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @Test
    void consultarFacturasExitosamente() throws Exception {
        ComandoModificarFactura comandoModificarFactura = new ComandoFacturaTestDataBuilder()
                .facturaExistentePorDefecto().buildActualizar();

        mockMvc.perform(get("/factura")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().is2xxSuccessful())
                .andExpect(jsonPath("$[0].id", is(comandoModificarFactura.getId().intValue())))
                .andExpect(jsonPath("$[0].tipoModalidad", is(comandoModificarFactura.getTipoModalidad().toString())))
                .andExpect(jsonPath("$[0].vehiculo.id", is(comandoModificarFactura.getVehiculo().getId().intValue())))
                .andExpect(jsonPath("$[0].estadoFactura", is(comandoModificarFactura.getEstadoFactura().toString())))
                .andReturn();
    }

    @Test
    void consultarFacturaPorIdExitosamente() throws Exception {
        ComandoModificarFactura comandoModificarFactura = new ComandoFacturaTestDataBuilder()
                .facturaExistentePorDefecto().buildActualizar();

        mockMvc.perform(get("/factura/3")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().is2xxSuccessful())
                .andExpect(jsonPath("$.id", is(comandoModificarFactura.getId().intValue())))
                .andExpect(jsonPath("$.tipoModalidad", is(comandoModificarFactura.getTipoModalidad().toString())))
                .andExpect(jsonPath("$.vehiculo.id", is(comandoModificarFactura.getVehiculo().getId().intValue())))
                .andExpect(jsonPath("$.estadoFactura", is(comandoModificarFactura.getEstadoFactura().toString())))
                .andReturn();

    }

    @Test
    void consultarFacturaPorVehiculoExitosamente() throws Exception {
        ComandoModificarFactura comandoModificarFactura = new ComandoFacturaTestDataBuilder()
                .facturaExistentePorDefecto().buildActualizar();

        mockMvc.perform(get("/factura")
                        .param("placa", "XOR435")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().is2xxSuccessful())
                .andExpect(jsonPath("$[0].id", is(comandoModificarFactura.getId().intValue())))
                .andExpect(jsonPath("$[0].tipoModalidad", is(comandoModificarFactura.getTipoModalidad().toString())))
                .andExpect(jsonPath("$[0].vehiculo.id", is(comandoModificarFactura.getVehiculo().getId().intValue())))
                .andExpect(jsonPath("$[0].estadoFactura", is(comandoModificarFactura.getEstadoFactura().toString())))
                .andReturn();
    }
}
