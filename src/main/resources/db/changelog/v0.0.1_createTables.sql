CREATE TABLE IF NOT EXISTS project(
    id BIGSERIAL PRIMARY KEY NOT NULL,
    link VARCHAR(255) NOT NULL,
    description TEXT,
    status BOOLEAN
);

CREATE TABLE IF NOT EXISTS priority(
    id BIGSERIAL PRIMARY KEY NOT NULL,
    priority_name VARCHAR(255) NOT NULL
);

CREATE TABLE IF NOT EXISTS task(
    id BIGSERIAL PRIMARY KEY NOT NULL,
    text TEXT,
    date_of_deadline TIMESTAMP,
    priority_id BIGINT REFERENCES priority(id) NOT NULL,
    project_id BIGINT REFERENCES project(id) NOT NULL
);

CREATE TABLE IF NOT EXISTS users(
    id BIGSERIAL PRIMARY KEY NOT NULL,
    username VARCHAR(255) NOT NULL UNIQUE,
    email VARCHAR(255) NOT NULL UNIQUE,
    activation_code VARCHAR(255) NOT NULL,
    activated_status BOOLEAN NOT NULL,
    password VARCHAR(255) NOT NULL,
    "role" VARCHAR(255) NOT NULL
);

CREATE TABLE IF NOT EXISTS user2project(
    id BIGSERIAL PRIMARY KEY NOT NULL,
    user_id BIGINT REFERENCES users(id) NOT NULL,
    project_id BIGINT REFERENCES project(id) NOT NULL,
    "role" VARCHAR(255) NOT NULL
);




