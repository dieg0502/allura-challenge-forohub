create table usuarios (
    id bigint generated always as identity primary key,
    nombre varchar(255) not null,
    correo_electronico varchar(255) not null unique,
    contrasena varchar(255) not null
);