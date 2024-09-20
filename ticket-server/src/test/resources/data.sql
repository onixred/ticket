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

-- role insert end

INSERT INTO pet_project.roles (
    name,
    active,
    created_at,
    updated_at
) VALUES (
    'Manager',
    true,
    current_timestamp,
    current_timestamp
);

-- role insert end

-- ticket insert end

INSERT INTO pet_project.tickets (
    client_id,
    author_id,
    manager_id,
    ticket_status_id,
    title,
    description,
    created_at,
    updated_at
) VALUES (
    1001,
    1001,
    1002,
    1,
    'Zakaz 2',
    'Opisanie zakaza',
    current_timestamp,
    current_timestamp
);

-- ticket insert end

-- ticket status insert start

INSERT INTO pet_project.ticket_statuses (
    name,
    active,
    created_at,
    updated_at,
    deleted
) VALUES (
    'Ticket created',
    true,
    current_timestamp,
    current_timestamp,
    false
);

-- ticket status insert end

-- user insert start

INSERT INTO pet_project.users (
    role_id,
    first_name,
    last_name,
    sur_name,
    full_name,
    login,
    password,
    active,
    suspended,
    created_at,
    updated_at
) VALUES (
    1,
    'Anton',
    'Kastrov',
    'Konstantinovych',
    'Kastrov Anton Konstantinovych',
    'tetlogin-kastrov',
    'passwordhash',
    true,
    false,
    current_timestamp,
    current_timestamp
);

-- user insert end