package com.ceiba.factura.configuracion;

import com.ceiba.factura.puerto.repositorio.RepositorioFactura;
import com.ceiba.factura.servicio.ServicioActualizarFactura;
import com.ceiba.factura.servicio.ServicioCrearFactura;
import com.ceiba.factura.servicio.ServicioEliminarFactura;
import com.ceiba.propietario.puerto.dao.DaoPropietario;
import com.ceiba.vehiculo.puerto.dao.DaoVehiculo;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class BeanServicioFactura {
    @Bean
    public ServicioCrearFactura servicioCrearFactura(RepositorioFactura repositorioFactura, DaoVehiculo daoVehiculo, DaoPropietario daoPropietario){
        return new ServicioCrearFactura(repositorioFactura, daoVehiculo, daoPropietario);
    }

    @Bean
    public ServicioActualizarFactura servicioActualizarFactura(RepositorioFactura repositorioFactura, DaoVehiculo daoVehiculo, DaoPropietario daoPropietario){
        return new ServicioActualizarFactura(repositorioFactura, daoVehiculo, daoPropietario);
    }

    @Bean
    public ServicioEliminarFactura servicioEliminarFactura(RepositorioFactura repositorioFactura){
        return new ServicioEliminarFactura(repositorioFactura);
    }
}
