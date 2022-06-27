package com.ceiba.vehiculo.modelo.entidad;

import com.ceiba.propietario.modelo.entidad.Propietario;
import com.ceiba.propietario.modelo.entidad.PropietarioTestDataBuilder;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class VehiculoTest {

    @Test
    void crearVehiculoExitosamente(){
        Propietario propietario = new PropietarioTestDataBuilder().conPropietarioPorDefecto().build();
        Vehiculo vehiculo = new VehiculoTestDataBuilder()
                .conId(1L)
                .conPropietario(propietario)
                .conPlaca("RTE024")
                .conDescripcion("Carro rojo marca Renault")
                .conTipoVehiculo(TipoVehiculo.CARRO).build();

        Assertions.assertEquals(1L, vehiculo.getId());
        Assertions.assertEquals("1000678940", vehiculo.getPropietario().getDocumento());
        Assertions.assertEquals("RTE024", vehiculo.getPlaca());
        Assertions.assertEquals("Carro rojo marca Renault", vehiculo.getDescripcion());
        Assertions.assertEquals(TipoVehiculo.CARRO, vehiculo.getTipoVehiculo());
    }
}
