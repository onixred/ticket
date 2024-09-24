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
    'Test1',
    'Test1',
    'Test1',
    'Test1 Test1 Test1',
    'test1@test.ru',
    current_timestamp,
    current_timestamp
);

INSERT INTO pet_project.clients (
    first_name,
    last_name,
    sur_name,
    full_name,
    email,
    created_at,
    updated_at
) VALUES (
    'Test2',
    'Test2',
    'Test2',
    'Test2 Test2 Test2',
    'test2@test.ru',
    current_timestamp,
    current_timestamp
);

INSERT INTO pet_project.clients (
    first_name,
    last_name,
    sur_name,
    full_name,
    email,
    created_at,
    updated_at,
    deleted
) VALUES (
    'Test3',
    'Test3',
    'Test3',
    'Test3 Test3 Test3',
    'test3@test.ru',
    current_timestamp,
    current_timestamp,
    true
);

INSERT INTO pet_project.clients (
    first_name,
    last_name,
    sur_name,
    full_name,
    email,
    created_at,
    updated_at
) VALUES (
    'Test5',
    'Test5',
    'Test5',
    'Test5 Test5 Test5',
    'test5@test.ru',
    current_timestamp,
    current_timestamp
);

INSERT INTO pet_project.clients (
    first_name,
    last_name,
    sur_name,
    full_name,
    email,
    created_at,
    updated_at
) VALUES (
    'Test6',
    'Test6',
    'Test6',
    'Test6 Test6 Test6',
    'test6@test.ru',
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
    888,
    1111111,
    '88881111111',
    current_timestamp,
    current_timestamp
);

INSERT INTO pet_project.phone_numbers (
    client_id,
    national_prefix,
    region_code,
    number,
    full_number,
    created_at,
    updated_at
) VALUES (
    1002,
    8,
    888,
    2222222,
    '88882222222',
    current_timestamp,
    current_timestamp
);

INSERT INTO pet_project.phone_numbers (
    client_id,
    national_prefix,
    region_code,
    number,
    full_number,
    created_at,
    updated_at,
    deleted
) VALUES (
    1003,
    8,
    888,
    3333333,
    '88883333333',
    current_timestamp,
    current_timestamp,
    true
);

INSERT INTO pet_project.phone_numbers (
    client_id,
    national_prefix,
    region_code,
    number,
    full_number,
    created_at,
    updated_at
) VALUES (
    1004,
    8,
    888,
    5555555,
    '88885555555',
    current_timestamp,
    current_timestamp
);

INSERT INTO pet_project.phone_numbers (
    client_id,
    national_prefix,
    region_code,
    number,
    full_number,
    created_at,
    updated_at
) VALUES (
    1005,
    8,
    888,
    6666666,
    '88886666666',
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
    'Guest',
    true,
    current_timestamp,
    current_timestamp
);

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

INSERT INTO pet_project.roles (
    name,
    active,
    created_at,
    updated_at
) VALUES (
    'Admin',
    true,
    current_timestamp,
    current_timestamp
);

INSERT INTO pet_project.roles (
    name,
    active,
    created_at,
    updated_at
) VALUES (
    'Test role 1',
    true,
    current_timestamp,
    current_timestamp
);

INSERT INTO pet_project.roles (
    name,
    active,
    created_at,
    updated_at
) VALUES (
    'Test role 2',
    true,
    current_timestamp,
    current_timestamp
);

INSERT INTO pet_project.roles (
    name,
    active,
    created_at,
    updated_at,
    deleted
) VALUES (
    'Test role 3',
    true,
    current_timestamp,
    current_timestamp,
    true
);

INSERT INTO pet_project.roles (
    name,
    active,
    created_at,
    updated_at
) VALUES (
    'Test role 5',
    true,
    current_timestamp,
    current_timestamp
);

-- role insert end

-- ticket status insert start

INSERT INTO pet_project.ticket_statuses (
    name,
    active,
    created_at,
    updated_at
) VALUES (
    'Processing',
    true,
    current_timestamp,
    current_timestamp
);

INSERT INTO pet_project.ticket_statuses (
    name,
    active,
    created_at,
    updated_at
) VALUES (
    'Collection information',
    true,
    current_timestamp,
    current_timestamp
);

INSERT INTO pet_project.ticket_statuses (
    name,
    active,
    created_at,
    updated_at
) VALUES (
    'Waiting for client',
    true,
    current_timestamp,
    current_timestamp
);

INSERT INTO pet_project.ticket_statuses (
    name,
    active,
    created_at,
    updated_at
) VALUES (
    'Finished',
    true,
    current_timestamp,
    current_timestamp
);

INSERT INTO pet_project.ticket_statuses (
    name,
    active,
    created_at,
    updated_at
) VALUES (
    'Test status 1',
    true,
    current_timestamp,
    current_timestamp
);

INSERT INTO pet_project.ticket_statuses (
    name,
    active,
    created_at,
    updated_at
) VALUES (
    'Test status 2',
    true,
    current_timestamp,
    current_timestamp
);

INSERT INTO pet_project.ticket_statuses (
    name,
    active,
    created_at,
    updated_at,
    deleted
) VALUES (
    'Test status 3',
    true,
    current_timestamp,
    current_timestamp,
    true
);

INSERT INTO pet_project.ticket_statuses (
    name,
    active,
    created_at,
    updated_at
) VALUES (
    'Test status 5',
    true,
    current_timestamp,
    current_timestamp
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
    'Test1',
    'Test1',
    'Test1',
    'Test1 Test1 Test1',
    'test1-login',
    'passwordhash',
    true,
    false,
    current_timestamp,
    current_timestamp
);

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
    'Test2',
    'Test2',
    'Test2',
    'Test2 Test2 Test2',
    'test2-login',
    'passwordhash',
    true,
    false,
    current_timestamp,
    current_timestamp
);

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
    updated_at,
    deleted
) VALUES (
    1,
    'Test3',
    'Test3',
    'Test3',
    'Test3 Test3 Test3',
    'test3-login',
    'passwordhash',
    true,
    false,
    current_timestamp,
    current_timestamp,
    true
);

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
    'Test5',
    'Test5',
    'Test5',
    'Test5 Test5 Test5',
    'test5-login',
    'passwordhash',
    true,
    false,
    current_timestamp,
    current_timestamp
);

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
    'Test6',
    'Test6',
    'Test6',
    'Test6 Test6 Test6',
    'test6-login',
    'passwordhash',
    true,
    false,
    current_timestamp,
    current_timestamp
);

-- user insert end

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
    1001,
    1,
    'Test ticket 1',
    'Ticket description',
    current_timestamp,
    current_timestamp
);

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
    1002,
    1002,
    1002,
    1,
    'Test ticket 2',
    'Ticket description',
    current_timestamp,
    current_timestamp
);

INSERT INTO pet_project.tickets (
    client_id,
    author_id,
    manager_id,
    ticket_status_id,
    title,
    description,
    created_at,
    updated_at,
    deleted
) VALUES (
    1003,
    1003,
    1003,
    1,
    'Test ticket 3',
    'Ticket description',
    current_timestamp,
    current_timestamp,
    true
);

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
    1004,
    1004,
    1004,
    1,
    'Test ticket 5',
    'Ticket description',
    current_timestamp,
    current_timestamp
);

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
    1005,
    1005,
    1005,
    1,
    'Test ticket 6',
    'Ticket description',
    current_timestamp,
    current_timestamp
);

-- ticket insert end