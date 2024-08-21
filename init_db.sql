CREATE TABLE IF NOT EXISTS ticket (
    id                      BIGINT           GENERATED ALWAYS AS IDENTITY,
    name                    VARCHAR(30)      NOT NULL,
    description             VARCHAR(200)     NOT NULL,

    PRIMARY KEY(id)
);