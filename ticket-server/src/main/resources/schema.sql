-- DROP SECTION

-- drop phone_numbers start

DROP INDEX IF EXISTS pet_project.phone_numbers_full_number_index;

DROP TABLE IF EXISTS pet_project.phone_numbers;

DROP SEQUENCE IF EXISTS pet_project.phone_numbers_id_pk_seq;

-- drop phone_numbers end

-- drop clients start

DROP INDEX IF EXISTS pet_project.clients_full_name_index;

DROP TABLE IF EXISTS pet_project.clients;

DROP SEQUENCE IF EXISTS pet_project.client_id_pk_seq;

-- drop clients end

DROP SCHEMA IF EXISTS pet_project;


-- CREATE SECTION

CREATE SCHEMA pet_project;

-- create clients start

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

CREATE INDEX clients_full_name_index ON pet_project.clients(full_name);

-- create clients end

-- create phone numbers start

CREATE SEQUENCE pet_project.phone_numbers_id_pk_seq START 1001;

CREATE TABLE pet_project.phone_numbers (
    phone_number_id BIGINT DEFAULT nextval('pet_project.phone_numbers_id_pk_seq'),
    client_id BIGINT,
    national_prefix INT NOT NULL,
    region_code INT NOT NULL,
    number INT NOT NULL,
    full_number INT NOT NULL UNIQUE,
    created_at TIMESTAMP NOT NULL,
    updated_at TIMESTAMP NOT NULL,
    deleted BOOLEAN NOT NULL DEFAULT false,
    PRIMARY KEY (phone_number_id, client_id)
);

CREATE INDEX phone_numbers_full_number_index ON pet_project.phone_numbers(full_number);

-- create phone numbers end