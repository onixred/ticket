-- DROP SECTION

-- drop users start

DROP SEQUENCE IF EXISTS pet_project.users_id_pk_seq;

DROP TABLE IF EXISTS pet_project.users;

-- drop users end

-- drop ticket statuses start

DROP SEQUENCE IF EXISTS pet_project.ticket_statuses_id_pk_seq;

DROP TABLE IF EXISTS pet_project.ticket_statuses;

-- drop ticket statuses end

-- drop tickets start

DROP SEQUENCE IF EXISTS pet_project.tickets_id_pk_seq;

DROP TABLE IF EXISTS pet_project.tickets;

-- drop tickets end

-- drop roles start

DROP SEQUENCE IF EXISTS pet_project.roles_id_pk_seq;

DROP TABLE IF EXISTS pet_project.roles;

-- drop roles end

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
    first_name VARCHAR(255) NOT NULL,
    last_name VARCHAR(255) NOT NULL,
    sur_name VARCHAR(255) NOT NULL,
    full_name VARCHAR(255) NOT NULL,
    email VARCHAR(255) UNIQUE NOT NULL,
    created_at TIMESTAMP NOT NULL,
    updated_at TIMESTAMP NOT NULL,
    deleted BOOLEAN NOT NULL DEFAULT false
);

CREATE INDEX clients_full_name_index ON pet_project.clients(full_name);

-- create clients end

-- create phone numbers start

CREATE SEQUENCE pet_project.phone_numbers_id_pk_seq START 1001;

CREATE TABLE pet_project.phone_numbers (
    phone_number_id BIGINT NOT NULL DEFAULT nextval('pet_project.phone_numbers_id_pk_seq'),
    client_id BIGINT NOT NULL,
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

-- create roles start

CREATE SEQUENCE pet_project.roles_id_pk_seq START 1;

CREATE TABLE pet_project.roles (
    role_id INT PRIMARY KEY DEFAULT nextval('pet_project.roles_id_pk_seq'),
    name VARCHAR(255) UNIQUE NOT NULL,
    active BOOLEAN NOT NULL DEFAULT false,
    created_at TIMESTAMP NOT NULL,
    updated_at TIMESTAMP NOT NULL,
    deleted BOOLEAN NOT NULL DEFAULT false
);

-- create roles end

-- create tickets start

CREATE SEQUENCE pet_project.tickets_id_pk_seq START 10000;

CREATE TABLE pet_project.tickets (
    ticket_id BIGINT NOT NULL DEFAULT nextval('pet_project.tickets_id_pk_seq'),
    client_id BIGINT NOT NULL,
    author_id BIGINT NOT NULL,
    manager_id BIGINT,
    ticket_status_id INT NOT NULL,
    title VARCHAR(255) NOT NULL,
    description VARCHAR,
    created_at TIMESTAMP NOT NULL,
    updated_at TIMESTAMP NOT NULL,
    deleted BOOLEAN NOT NULL DEFAULT false
);

-- create tickets end

-- create tickets statuses start

CREATE SEQUENCE pet_project.ticket_statuses_id_pk_seq START 1;

CREATE TABLE pet_project.ticket_statuses (
    ticket_status_id INT PRIMARY KEY DEFAULT nextval('pet_project.ticket_statuses_id_pk_seq'),
    name VARCHAR(255) UNIQUE NOT NULL,
    active BOOLEAN NOT NULL DEFAULT false,
    created_at TIMESTAMP NOT NULL,
    updated_at TIMESTAMP NOT NULL,
    deleted BOOLEAN NOT NULL DEFAULT false
);

-- create ticket statuses end

-- create users start

CREATE SEQUENCE pet_project.users_id_pk_seq START 1000;

CREATE TABLE pet_project.users (
    user_id BIGINT PRIMARY KEY DEFAULT nextval('pet_project.users_id_pk_seq'),
    role_id INT NOT NULL,
    first_name VARCHAR(255) NOT NULL,
    last_name VARCHAR(255) NOT NULL,
    sur_name VARCHAR(255) NOT NULL,
    full_name VARCHAR(255) NOT NULL,
    login VARCHAR(255) UNIQUE NOT NULL,
    password VARCHAR(255) NOT NULL,
    active BOOLEAN NOT NULL DEFAULT false,
    suspended BOOLEAN NOT NULL DEFAULT false,
    created_at TIMESTAMP NOT NULL,
    updated_at TIMESTAMP NOT NULL,
    deleted BOOLEAN NOT NULL DEFAULT false
)

-- create users end
