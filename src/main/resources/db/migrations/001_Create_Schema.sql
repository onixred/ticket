DROP SCHEMA IF EXISTS ticket_schema CASCADE;

CREATE SCHEMA IF NOT EXISTS ticket_schema;

CREATE TABLE IF NOT EXISTS ticket_schema.ticket
(
    id SERIAL PRIMARY KEY,
    title varchar(255) UNIQUE NOT NULL,
    description varchar(4096)
);
