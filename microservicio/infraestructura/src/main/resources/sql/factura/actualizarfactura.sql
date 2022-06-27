update factura
set tipo_modalidad = :tipo_modalidad,
    id_vehiculo = :id_vehiculo,
    fecha_inicio = :fecha_inicio,
    fecha_fin = :fecha_fin,
    valor = :valor,
    placa = :placa,
    estado = :estado
where id = :id