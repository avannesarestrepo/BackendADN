package com.ceiba.factura.controlador;

import com.ceiba.ApplicationMock;
import com.ceiba.factura.comando.ComandoFactura;
import com.ceiba.factura.modelo.dto.FacturaDTO;
import com.ceiba.factura.modelo.entidad.Factura;
import com.ceiba.factura.modelo.entidad.TipoModalidadFactura;
import com.ceiba.factura.puerto.dao.DaoFactura;
import com.ceiba.factura.puerto.repositorio.RepositorioFactura;
import com.ceiba.propietario.comando.ComandoPropietario;
import com.ceiba.propietario.controlador.ComandoPropietarioTestDataBuilder;
import com.ceiba.propietario.controlador.RespuestaPropietario;
import com.ceiba.propietario.modelo.dto.PropietarioDTO;
import com.fasterxml.jackson.core.JsonProcessingException;
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

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(SpringExtension.class)
@WebMvcTest(ComandoControladorFactura.class)
@ContextConfiguration(classes = ApplicationMock.class)
@DirtiesContext(classMode = DirtiesContext.ClassMode.AFTER_EACH_TEST_METHOD)
public class ComandoControladorFacturaTest {
    @Autowired
    private ObjectMapper objectMapper;

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private RepositorioFactura repositorioFactura;

    @Autowired
    private DaoFactura daoFactura;

    @Test
    void guardarFacturaExitosamente() throws Exception {
        ComandoFactura comandoFactura = new ComandoFacturaTestDataBuilder().crearFacturaMensualPorDefecto().build();

        MvcResult resultado = mockMvc.perform(post("/factura")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(comandoFactura)))
                .andExpect(status().is2xxSuccessful()).andReturn();

        String jsonResultado = resultado.getResponse().getContentAsString();
        RespuestaFactura respuesta = objectMapper.readValue(jsonResultado, RespuestaFactura.class);

        FacturaDTO facturaDTO = daoFactura.obtenerPorId(respuesta.getValor());

        Assertions.assertEquals(2L, facturaDTO.getIdFactura());
        Assertions.assertEquals(TipoModalidadFactura.MENSUAL, facturaDTO.getTipoModalidad());
    }

    @Test
    void actualizarFacturaExitosamente() throws Exception {
        ComandoFactura comandoFactura = new ComandoFacturaTestDataBuilder().facturaExistentePorDefecto().build();
        comandoFactura.setTipoModalidad(TipoModalidadFactura.QUINCENAL);

        mockMvc.perform(put("/factura/3")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(comandoFactura)))
                .andExpect(status().is2xxSuccessful()).andReturn();

        FacturaDTO facturaDTO = daoFactura.obtenerPorId(comandoFactura.getIdFactura());

        Assertions.assertEquals(TipoModalidadFactura.QUINCENAL, facturaDTO.getTipoModalidad());
    }

    @Test
    void eliminarFacturaExitosamente() throws Exception {
        mockMvc.perform(delete("/factura/3")
                .contentType(MediaType.APPLICATION_JSON))
        .andExpect(status().is2xxSuccessful()).andReturn();
    }
}
