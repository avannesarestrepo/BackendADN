update factura
set tipo_modalidad = :tipo_modalidad,
    id_propietario = :id_propietario,
    id_vehiculo = :id_vehiculo,
    valor = :valor,
    descripcion = :descripcion,
    estado = :estado
where id_factura = :id_factura