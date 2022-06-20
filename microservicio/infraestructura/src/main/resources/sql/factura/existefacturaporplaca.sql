select count(1)
from factura f
left join vehiculo v on f.id_vehiculo = v.id_vehiculo
where id_vehiculo = :id_vehiculo