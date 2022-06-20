package com.ceiba.propietario.configuracion;

import com.ceiba.propietario.puerto.repositorio.RepositorioPropietario;
import com.ceiba.propietario.servicio.ServicioActualizarPropietario;
import com.ceiba.propietario.servicio.ServicioCrearPropietario;
import com.ceiba.propietario.servicio.ServiciosEliminarPropietario;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class BeanServicioPropietario {

    @Bean
    public ServicioCrearPropietario servicioCrearPropietario(RepositorioPropietario repositorioPropietario) {
        return new ServicioCrearPropietario(repositorioPropietario);
    }

    @Bean
    public ServicioActualizarPropietario servicioActualizarPropietario(RepositorioPropietario repositorioPropietario) {
        return new ServicioActualizarPropietario(repositorioPropietario);
    }

    @Bean
    public ServiciosEliminarPropietario serviciosEliminarPropietario(RepositorioPropietario repositorioPropietario){
        return new ServiciosEliminarPropietario(repositorioPropietario);
    }
}
