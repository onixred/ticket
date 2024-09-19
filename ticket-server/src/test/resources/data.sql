-- client insert start

INSERT INTO pet_project.clients (
    first_name,
    last_name,
    sur_name,
    full_name,
    email,
    created_at,
    updated_at
) VALUES (
    'Valeryi',
    'Grobovshik',
    'Antonovych',
    'Grobovshik Valeryi Antonovych',
    'grobvalant@mail.ru',
    current_timestamp,
    current_timestamp
);

-- client insert end

-- phone number insert start

INSERT INTO pet_project.phone_numbers (
    client_id,
    national_prefix,
    region_code,
    number,
    full_number,
    created_at,
    updated_at
) VALUES (
    1001,
    8,
    855,
    3414352,
    '88553414352',
    current_timestamp,
    current_timestamp
);

-- phone number insert end