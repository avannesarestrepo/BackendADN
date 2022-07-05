package com.ceiba.factura.adaptador.repositorio;

import com.ceiba.factura.modelo.entidad.EstadoFactura;
import com.ceiba.factura.modelo.entidad.Factura;
import com.ceiba.factura.puerto.repositorio.RepositorioFactura;
import com.ceiba.infraestructura.jdbc.CustomNamedParameterJdbcTemplate;
import com.ceiba.infraestructura.jdbc.EjecucionBaseDeDatos;
import com.ceiba.infraestructura.jdbc.sqlstatement.SqlStatement;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.stereotype.Repository;

@Repository
public class RepositorioFacturaMysql implements RepositorioFactura {

    private static final String ID = "id";
    private static final String TIPO_MODALIDAD = "tipo_modalidad";
    private static final String VEHICULO = "id_vehiculo";
    private static final String FECHA_INICIO = "fecha_inicio";
    private static final String FECHA_FIN = "fecha_fin";
    private static final String VALOR = "valor";
    private static final String PLACA = "placa";
    private static final String ESTADO = "estado";

    private final CustomNamedParameterJdbcTemplate customNamedParameterJdbcTemplate;

    private final MapeoFactura mapeoFactura;

    @SqlStatement(namespace = "factura", value = "crearfactura")
    private static String sqlCrearFactura;

    @SqlStatement(namespace = "factura", value = "actualizarfactura")
    private static String sqlActualizarFactura;

    @SqlStatement(namespace = "factura", value = "anularfactura")
    private static String sqlAnularFactura;

    @SqlStatement(namespace = "factura", value = "obtenerfacturaporid")
    private static String sqlObtenerFacturaPorId;

    public RepositorioFacturaMysql(CustomNamedParameterJdbcTemplate customNamedParameterJdbcTemplate, MapeoFactura mapeoFactura) {
        this.customNamedParameterJdbcTemplate = customNamedParameterJdbcTemplate;
        this.mapeoFactura = mapeoFactura;
    }

    @Override
    public Long crear(Factura factura) {
        return this.customNamedParameterJdbcTemplate.crear(generadorParametros(factura), sqlCrearFactura);
    }

    @Override
    public void actualizar(Factura factura) {
        this.customNamedParameterJdbcTemplate.actualizar(generadorParametros(factura), sqlActualizarFactura);
    }

    @Override
    public void anular(Long id) {
        MapSqlParameterSource parameterSource = new MapSqlParameterSource();
        parameterSource.addValue(ESTADO, String.valueOf(EstadoFactura.ANULADA));
        parameterSource.addValue(ID, id);
        this.customNamedParameterJdbcTemplate.actualizar(parameterSource, sqlAnularFactura);
    }

    @Override
    public Factura obtenerFacturaPorId(Long id) {
        MapSqlParameterSource parameterSource = new MapSqlParameterSource();
        parameterSource.addValue(ID, id);
        return EjecucionBaseDeDatos.obtenerUnObjetoONull(() ->
                this.customNamedParameterJdbcTemplate.getNamedParameterJdbcTemplate()
                        .queryForObject(sqlObtenerFacturaPorId, parameterSource, mapeoFactura));
    }

    private MapSqlParameterSource generadorParametros(Factura factura){
        MapSqlParameterSource parameterSource = new MapSqlParameterSource();
        parameterSource.addValue(ID, factura.getId());
        parameterSource.addValue(TIPO_MODALIDAD, String.valueOf(factura.getTipoModalidad()));
        parameterSource.addValue(VEHICULO, factura.getVehiculo().getId());
        parameterSource.addValue(FECHA_INICIO, factura.getFechaInicio());
        parameterSource.addValue(FECHA_FIN, factura.getFechaFin());
        parameterSource.addValue(VALOR, factura.getValor());
        parameterSource.addValue(PLACA, factura.getPlaca());
        parameterSource.addValue(ESTADO, String.valueOf(factura.getEstadoFactura()));

        return parameterSource;
    }
}
