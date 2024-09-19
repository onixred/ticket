DROP TABLE IF EXISTS pet_project.clients;

DROP SEQUENCE IF EXISTS pet_project.client_id_pk_seq;

DROP SCHEMA IF EXISTS pet_project;

CREATE SCHEMA pet_project;

CREATE SEQUENCE pet_project.client_id_pk_seq START 1001;

CREATE TABLE pet_project.clients (
    client_id BIGINT PRIMARY KEY DEFAULT nextval('pet_project.client_id_pk_seq'),
    first_name VARCHAR NOT NULL,
    last_name VARCHAR NOT NULL,
    sur_name VARCHAR NOT NULL,
    full_name VARCHAR NOT NULL,
    email VARCHAR UNIQUE NOT NULL,
    created_at TIMESTAMP NOT NULL,
    updated_at TIMESTAMP NOT NULL,
    deleted BOOLEAN NOT NULL DEFAULT false
);

CREATE SEQUENCE pet_project.phone_numbers_id_pk_seq START 1001;