CREATE TABLE area
(
    id BIGINT PRIMARY KEY,
    name VARCHAR NOT NULL
);

CREATE TABLE employer
(
    id BIGINT PRIMARY KEY,
    name VARCHAR NOT NULL,
    date_create TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
    description TEXT,
    area_id BIGINT NOT NULL,
    comment TEXT,
    view_count BIGINT NOT NULL DEFAULT 0,
    FOREIGN KEY(area_id) REFERENCES area(id) ON DELETE CASCADE
);

CREATE TABLE vacancy
(
    id BIGINT PRIMARY KEY,
    name VARCHAR NOT NULL,
    date_create TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
    area_id BIGINT NOT NULL,
    salary_to BIGINT,
    salary_from BIGINT,
    salary_currency VARCHAR,
    salary_gross BOOLEAN,
    created_at TIMESTAMP NOT NULL,
    employer_id BIGINT NOT NULL,
    view_count BIGINT NOT NULL DEFAULT 0,
    comment TEXT,
    FOREIGN KEY(area_id) REFERENCES area(id) ON DELETE CASCADE,
    FOREIGN KEY(employer_id) REFERENCES employer(id) ON DELETE CASCADE
);