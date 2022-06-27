package com.ceiba.factura.consulta;

import com.ceiba.factura.modelo.dto.FacturaDTO;
import com.ceiba.factura.puerto.dao.DaoFactura;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class ManejadorConsultaFacturas {

    private final DaoFactura daoFactura;

    public ManejadorConsultaFacturas(DaoFactura daoFactura) {
        this.daoFactura = daoFactura;
    }

    public List<FacturaDTO> obtenerFacturas(){
        return daoFactura.obtenerFacturas();
    }

    public FacturaDTO obtenerFacturaPorId(Long id){
        return daoFactura.obtenerFacturaPorId(id);
    }

    public List<FacturaDTO> obtenerFacturasPorVehiculo(String placa){
        return daoFactura.obtenerFacturasPorVehiculo(placa);
    }
}
