CREATE TABLE role (
    id VARCHAR(36) PRIMARY KEY,
    name VARCHAR(50) UNIQUE NOT NULL,
    created_at TIMESTAMP,
    updated_at TIMESTAMP,
    deleted BOOLEAN
);

CREATE TABLE users (
    id VARCHAR(36) PRIMARY KEY,
    full_name VARCHAR(100),
    email VARCHAR(120) UNIQUE,
    password VARCHAR(255),
    is_active BOOLEAN,
    role_id VARCHAR(36),
    created_at TIMESTAMP,
    updated_at TIMESTAMP,
    deleted BOOLEAN,
    CONSTRAINT fk_role FOREIGN KEY (role_id) REFERENCES role(id)
);