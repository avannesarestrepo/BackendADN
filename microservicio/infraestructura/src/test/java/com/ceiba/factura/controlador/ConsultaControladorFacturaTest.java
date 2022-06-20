package com.ceiba.factura.controlador;

import com.ceiba.ApplicationMock;
import com.ceiba.factura.comando.ComandoFactura;
import com.ceiba.propietario.controlador.ConsultaControladorPropietario;
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

import static org.hamcrest.core.Is.is;
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
    void consultarFacturaPorIdExitosa() throws Exception {
        ComandoFactura comandoFactura = new ComandoFacturaTestDataBuilder().facturaExistentePorDefecto().build();

        mockMvc.perform(get("/factura")
                .param("id", "3")
                .contentType(MediaType.APPLICATION_JSON))
        .andExpect(status().is2xxSuccessful())
        .andExpect(jsonPath("$.idFactura", is(comandoFactura.getIdFactura().intValue())))
        .andExpect(jsonPath("$.tipoModalidad", is(comandoFactura.getTipoModalidad().toString())))
        .andExpect(jsonPath("$.valor", is(comandoFactura.getValor())))
        .andExpect(jsonPath("$.estado", is(comandoFactura.getEstado())))
        .andReturn();
    }
}
