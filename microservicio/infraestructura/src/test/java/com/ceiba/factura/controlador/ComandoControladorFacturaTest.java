package com.ceiba.factura.controlador;

import com.ceiba.ApplicationMock;
import com.ceiba.factura.comando.ComandoModificarFactura;
import com.ceiba.factura.comando.ComandoSolicitarFactura;
import com.ceiba.factura.modelo.entidad.EstadoFactura;
import com.ceiba.factura.modelo.entidad.Factura;
import com.ceiba.factura.modelo.entidad.TipoModalidad;
import com.ceiba.factura.puerto.repositorio.RepositorioFactura;
import com.ceiba.vehiculo.puerto.repositorio.RepositorioVehiculo;
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

import java.math.BigDecimal;

import static org.hamcrest.Matchers.is;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
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
    RepositorioFactura repositorioFactura;

    @Autowired
    RepositorioVehiculo repositorioVehiculo;

    @Test
    void crearFacturaExitosamente() throws Exception {
        ComandoSolicitarFactura comandoSolicitarFactura = new ComandoFacturaTestDataBuilder()
                .crearPorDefectoFactura().build();

        MvcResult resultado = mockMvc.perform(post("/factura")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(comandoSolicitarFactura)))
                .andExpect(status().is2xxSuccessful()).andReturn();

        String jsonResultado = resultado.getResponse().getContentAsString();
        RespuestaFactura respuesta = objectMapper.readValue(jsonResultado, RespuestaFactura.class);

        Factura factura = repositorioFactura.obtenerFacturaPorId(respuesta.getValor());

        Assertions.assertEquals("XOR435", repositorioVehiculo.obtenerVehiculoPorId(factura.getVehiculo().getId()).getPlaca());
        Assertions.assertEquals(TipoModalidad.MENSUAL, factura.getTipoModalidad());
        Assertions.assertEquals(EstadoFactura.CONFIRMADA, factura.getEstadoFactura());
        Assertions.assertEquals(BigDecimal.valueOf(160000), factura.getValor());
    }

    @Test
    void anularFacturaExitosamente() throws Exception {

        mockMvc.perform(put("/factura")
                        .param("id", "3")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());

        Factura facturaAnulada = repositorioFactura.obtenerFacturaPorId(3L);
        Assertions.assertEquals(EstadoFactura.ANULADA, facturaAnulada.getEstadoFactura());
    }

    @Test
    void actualizarFacturaExitosamente() throws Exception {
        ComandoModificarFactura comandoModificarFactura = new ComandoFacturaTestDataBuilder().facturaExistentePorDefecto().buildActualizar();
        comandoModificarFactura.setTipoModalidad(TipoModalidad.QUINCENAL);

        mockMvc.perform(put("/factura/3")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(comandoModificarFactura)))
                .andExpect(status().is2xxSuccessful()).andReturn();

        Factura factura = repositorioFactura.obtenerFacturaPorId(comandoModificarFactura.getId());

        Assertions.assertEquals("XOR435", repositorioVehiculo.obtenerVehiculoPorId(factura.getVehiculo().getId()).getPlaca());
        Assertions.assertEquals(TipoModalidad.QUINCENAL, factura.getTipoModalidad());
        Assertions.assertEquals(EstadoFactura.CONFIRMADA, factura.getEstadoFactura());
        Assertions.assertEquals(BigDecimal.valueOf(104000), factura.getValor());
    }
}
