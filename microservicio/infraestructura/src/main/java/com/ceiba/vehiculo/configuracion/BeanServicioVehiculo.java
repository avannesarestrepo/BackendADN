package com.ceiba.vehiculo.configuracion;

import com.ceiba.propietario.puerto.repositorio.RepositorioPropietario;
import com.ceiba.vehiculo.puerto.repositorio.RepositorioVehiculo;
import com.ceiba.vehiculo.servicio.ServicioActualizarVehiculo;
import com.ceiba.vehiculo.servicio.ServicioCrearVehiculo;
import com.ceiba.vehiculo.servicio.ServicioEliminarVehiculo;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class BeanServicioVehiculo {

    @Bean
    public ServicioCrearVehiculo servicioCrearVehiculo(RepositorioVehiculo repositorioVehiculo, RepositorioPropietario repositorioPropietario){
        return new ServicioCrearVehiculo(repositorioVehiculo, repositorioPropietario);
    }

    @Bean
    public ServicioActualizarVehiculo servicioActualizarVehiculo(RepositorioVehiculo repositorioVehiculo, RepositorioPropietario repositorioPropietario){
        return new ServicioActualizarVehiculo(repositorioVehiculo, repositorioPropietario);
    }

    @Bean
    public ServicioEliminarVehiculo serviciosEliminarVehiculo(RepositorioVehiculo repositorioVehiculo){
        return new ServicioEliminarVehiculo(repositorioVehiculo);
    }
}
