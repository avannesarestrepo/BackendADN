package com.ceiba.factura.adaptador.dao;

import com.ceiba.factura.modelo.dto.FacturaDTO;
import com.ceiba.factura.puerto.dao.DaoFactura;
import com.ceiba.infraestructura.jdbc.CustomNamedParameterJdbcTemplate;
import com.ceiba.infraestructura.jdbc.EjecucionBaseDeDatos;
import com.ceiba.infraestructura.jdbc.sqlstatement.SqlStatement;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class DaoFacturaMysql implements DaoFactura {

    private static final String ID = "id";
    private static final String PLACA = "placa";

    private final CustomNamedParameterJdbcTemplate customNamedParameterJdbcTemplate;

    private final MapeoFacturaDTO mapeoFacturaDTO;

    @SqlStatement(namespace = "factura", value = "obtenerfacturaporid")
    private static String sqlObtenerFacturaPorId;

    @SqlStatement(namespace = "factura", value = "obtenerfacturas")
    private static String sqlObtenerFacturas;

    @SqlStatement(namespace = "factura", value = "obtenerfacturaporvehiculo")
    private static String sqlObtenerFacturaPorVehiculo;

    public DaoFacturaMysql(CustomNamedParameterJdbcTemplate customNamedParameterJdbcTemplate, MapeoFacturaDTO mapeoFacturaDTO) {
        this.customNamedParameterJdbcTemplate = customNamedParameterJdbcTemplate;
        this.mapeoFacturaDTO = mapeoFacturaDTO;
    }

    @Override
    public List<FacturaDTO> obtenerFacturas() {
        return this.customNamedParameterJdbcTemplate.getNamedParameterJdbcTemplate()
                        .query(sqlObtenerFacturas, mapeoFacturaDTO);
    }

    @Override
    public FacturaDTO obtenerFacturaPorId(Long id) {
        MapSqlParameterSource parameterSource = new MapSqlParameterSource();
        parameterSource.addValue(ID, id);
        return EjecucionBaseDeDatos.obtenerUnObjetoONull(() ->
                this.customNamedParameterJdbcTemplate.getNamedParameterJdbcTemplate()
                        .queryForObject(sqlObtenerFacturaPorId, parameterSource, mapeoFacturaDTO));
    }

    @Override
    public List<FacturaDTO> obtenerFacturasPorVehiculo(String placa) {
        MapSqlParameterSource parameterSource = new MapSqlParameterSource();
        parameterSource.addValue(PLACA, placa);
        return this.customNamedParameterJdbcTemplate.getNamedParameterJdbcTemplate()
                .query(sqlObtenerFacturaPorVehiculo, parameterSource, mapeoFacturaDTO);
    }
}
