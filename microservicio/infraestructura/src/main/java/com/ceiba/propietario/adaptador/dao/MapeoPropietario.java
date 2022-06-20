package com.ceiba.propietario.adaptador.dao;

import com.ceiba.infraestructura.jdbc.MapperResult;
import com.ceiba.propietario.modelo.dto.PropietarioDTO;
import com.ceiba.propietario.modelo.entidad.TipoDocumentoPropietario;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;

import java.sql.ResultSet;
import java.sql.SQLException;

@Component
public class MapeoPropietario implements RowMapper<PropietarioDTO>, MapperResult {
    @Override
    public PropietarioDTO mapRow(ResultSet rs, int rowNum) throws SQLException {
        Long id = rs.getLong("id");
        TipoDocumentoPropietario tipoDocumento = TipoDocumentoPropietario.valueOf(rs.getString("tipo_documento"));
        String documento = rs.getString("documento");
        String nombreCompleto = rs.getString("nombre_completo");
        String email = rs.getString("email");
        Integer telefono = rs.getInt("telefono");

        return new PropietarioDTO(id, tipoDocumento, documento, nombreCompleto, email, telefono);
    }
}
