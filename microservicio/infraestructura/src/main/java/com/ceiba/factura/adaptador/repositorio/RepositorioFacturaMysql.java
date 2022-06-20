package com.ceiba.factura.adaptador.repositorio;

import com.ceiba.factura.modelo.entidad.Factura;
import com.ceiba.factura.puerto.repositorio.RepositorioFactura;
import com.ceiba.infraestructura.jdbc.CustomNamedParameterJdbcTemplate;
import com.ceiba.infraestructura.jdbc.sqlstatement.SqlStatement;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.stereotype.Repository;

@Repository
public class RepositorioFacturaMysql implements RepositorioFactura {

    private static final String ID_FACTURA = "id_factura";
    private static final String TIPO_MODALIDAD = "tipo_modalidad";
    private static final String ID_PROPIETARIO = "id_propietario";
    private static final String ID_VEHICULO = "id_vehiculo";
    private static final String FECHA_INICIO = "fecha_inicio";
    private static final String FECHA_FIN = "fecha_fin";
    private static final String VALOR = "valor";
    private static final String DESCRIPCION = "descripcion";
    private static final String ESTADO = "estado";

    private final CustomNamedParameterJdbcTemplate customNamedParameterJdbcTemplate;

    @SqlStatement(namespace = "factura", value = "guardarfactura")
    private static String sqlGuardarFactura;

    @SqlStatement(namespace = "factura", value = "actualizarfactura")
    private static String sqlActualizarFactura;

    @SqlStatement(namespace = "factura", value = "eliminarfactura")
    private static String sqlEliminarFactura;

    @SqlStatement(namespace = "factura", value = "existefacturaporid")
    private static String sqlExisteFacturaPorId;

    @SqlStatement(namespace = "factura", value = "existefacturaporplaca")
    private static String sqlExisteFacturaPorPlaca;

    public RepositorioFacturaMysql(CustomNamedParameterJdbcTemplate customNamedParameterJdbcTemplate) {
        this.customNamedParameterJdbcTemplate = customNamedParameterJdbcTemplate;
    }

    @Override
    public Long guardar(Factura factura) {
        MapSqlParameterSource parameterSource = generadorParametros(factura);
        return this.customNamedParameterJdbcTemplate.crear(parameterSource, sqlGuardarFactura, ID_FACTURA);
    }

    @Override
    public void actualizar(Factura factura) {
        MapSqlParameterSource parameterSource = generadorParametros(factura);
        this.customNamedParameterJdbcTemplate.actualizar(parameterSource, sqlActualizarFactura);
    }

    @Override
    public void eliminar(Long id) {
        MapSqlParameterSource parameterSource = new MapSqlParameterSource();
        parameterSource.addValue(ID_FACTURA, id);
        this.customNamedParameterJdbcTemplate.getNamedParameterJdbcTemplate().update(sqlEliminarFactura, parameterSource);
    }

    @Override
    public Boolean existeConId(Long id) {
        MapSqlParameterSource parameterSource = new MapSqlParameterSource();
        parameterSource.addValue(ID_FACTURA, id);
        return this.customNamedParameterJdbcTemplate.getNamedParameterJdbcTemplate().queryForObject
                (sqlExisteFacturaPorId, parameterSource, Boolean.class);
    }

    private MapSqlParameterSource generadorParametros(Factura factura){
        MapSqlParameterSource parameterSource = new MapSqlParameterSource();
        parameterSource.addValue(ID_FACTURA, factura.getIdFactura());
        parameterSource.addValue(TIPO_MODALIDAD, String.valueOf(factura.getTipoModalidad()));
        parameterSource.addValue(ID_PROPIETARIO, factura.getIdPropietario());
        parameterSource.addValue(ID_VEHICULO, factura.getIdVehiculo());
        parameterSource.addValue(FECHA_INICIO, factura.getFechaInicio());
        parameterSource.addValue(FECHA_FIN, factura.getFechaFin());
        parameterSource.addValue(VALOR, factura.getValor());
        parameterSource.addValue(DESCRIPCION, factura.getDescripcion());
        parameterSource.addValue(ESTADO, factura.getEstado());

        return  parameterSource;
    }
}
