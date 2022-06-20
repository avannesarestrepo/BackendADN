insert into propietario(id, tipo_documento, documento, nombre_completo, email, telefono)
values (2, 'CC', 54321, 'Angie Vannesa', 'ava@gmail.com', 87565);

insert into propietario(id, tipo_documento, documento, nombre_completo, email, telefono)
values (3, 'CC', 98743, 'Angie Restrepo', 'ava@gmail.com', 34234);

insert into vehiculo(id_vehiculo, id_propietario, placa, descripcion, tipo_vehiculo)
values (2, 3, 'XOR435', 'Carro Gris Renault', 'CARRO');

insert into factura(id_factura, tipo_modalidad, id_propietario, id_vehiculo, fecha_inicio, fecha_fin, valor, descripcion, estado)
values (3, 'MENSUAL', 3, 2, '2022-06-16','2022-07-16', 104000, '', true);