create table propietario (
 id bigint(11) not null auto_increment,
 tipo_documento varchar(5) not null,
 documento varchar(30) not null,
 nombre_completo varchar(100) not null,
 email varchar(100) not null,
 telefono int not null,
 primary key (id)
);

create table vehiculo(
 id_vehiculo bigint(11) not null auto_increment,
 id_propietario bigint(11) not null,
 placa varchar(6) not null,
 descripcion varchar(100),
 tipo_vehiculo varchar(50) not null,
 primary key (id_vehiculo),
 foreign key (id_propietario) references propietario(id)
);

create table factura(
 id_factura bigint(11) not null auto_increment,
 tipo_modalidad varchar(30) not null,
 id_propietario bigint(11),
 id_vehiculo bigint(11),
 fecha_inicio timestamp not null,
 fecha_fin timestamp not null,
 valor double not null,
 descripcion varchar(50),
 estado tinyint not null,
 primary key (id_factura)
)