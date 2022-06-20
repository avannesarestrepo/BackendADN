package com.ceiba.factura.consulta;

import com.ceiba.factura.modelo.dto.FacturaDTO;
import com.ceiba.factura.puerto.dao.DaoFactura;
import com.ceiba.vehiculo.puerto.dao.DaoVehiculo;
import org.springframework.stereotype.Component;

@Component
public class ManejadorConsultaFactura {

    private final DaoFactura daoFactura;

    public ManejadorConsultaFactura(DaoFactura daoFactura) {
        this.daoFactura = daoFactura;
    }

    public FacturaDTO obtenerPorId(Long id){return daoFactura.obtenerPorId(id);}
}
