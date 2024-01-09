CREATE TABLE users(
    id BIGSERIAL PRIMARY KEY,
    name VARCHAR(100) NOT NULL,
    email VARCHAR(50) UNIQUE NOT NULL,
    password VARCHAR(80) NOT NULL,
    enabled BOOLEAN NOT NULL,
    role VARCHAR(50)
);