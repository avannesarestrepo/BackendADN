package com.ceiba.factura.configuracion;

import com.ceiba.factura.puerto.repositorio.RepositorioFactura;
import com.ceiba.factura.servicio.ServicioAnularFactura;
import com.ceiba.factura.servicio.ServicioFacturar;
import com.ceiba.factura.servicio.ServicioModificarFactura;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class BeanServicioFactura {

    @Bean
    public ServicioFacturar servicioFacturar(RepositorioFactura repositorioFactura){
        return new ServicioFacturar(repositorioFactura);
    }

    @Bean
    public ServicioModificarFactura servicioModificarFactura(RepositorioFactura repositorioFactura){
        return new ServicioModificarFactura(repositorioFactura);
    }

    @Bean
    public ServicioAnularFactura servicioAnularFactura(RepositorioFactura repositorioFactura){
        return new ServicioAnularFactura(repositorioFactura);
    }
}
