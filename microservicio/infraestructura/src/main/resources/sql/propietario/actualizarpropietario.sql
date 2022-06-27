update propietario
set tipo_documento = :tipo_documento,
    documento = :documento,
    nombre_completo = :nombre_completo,
    email = :email,
    telefono = :telefono
where id = :id