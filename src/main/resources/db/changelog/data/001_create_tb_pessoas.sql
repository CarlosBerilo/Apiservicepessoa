--liquibase formatted sql

--changeset carlos:001
create table tb_pessoas (
    id BIGSERIAL PRIMARY KEY UNIQUE NOT NULL,
    codigo varchar(510) UNIQUE NOT NULL,
    nome varchar(255) NOT NULL,
    email varchar(255),
    created_at TIMESTAMP NOT NULL,
    update_at TIMESTAMP NOT NULL
);
