CREATE SCHEMA IF NOT EXISTS pet_project;

CREATE SEQUENCE IF NOT EXISTS pet_project.client_id_pk_seq START 1000;

CREATE TABLE IF NOT EXISTS pet_project.clients (
    client_id BIGINT PRIMARY KEY DEFAULT nextval('client_id_pk_seq'),
    first_name VARCHAR NOT NULL,
    last_name VARCHAR NOT NULL,
    sur_name VARCHAR NOT NULL,
    full_name VARCHAR NOT NULL,
    email VARCHAR UNIQUE NOT NULL,
    created_at TIMESTAMP NOT NULL DEFAULT now(),
    updated_at TIMESTAMP NOT NULL DEFAULT now(),
    deleted BOOLEAN NOT NULL DEFAULT false
);