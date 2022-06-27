select f.id, f.tipo_modalidad, f.id_vehiculo, f.fecha_inicio, f.fecha_fin, f.valor, f.placa, f.estado
from factura f
left join vehiculo v on f.id_vehiculo = v.id
where v.placa = :placa