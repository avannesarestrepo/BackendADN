select id_factura, tipo_modalidad, id_propietario, id_vehiculo, fecha_inicio, fecha_fin, valor, descripcion, estado
from factura
where id_factura = :id_factura