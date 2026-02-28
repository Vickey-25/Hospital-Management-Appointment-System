CREATE TABLE doctor_profiles (
    id VARCHAR(36) PRIMARY KEY,
    specialization VARCHAR(100),
    experience_years INT,
    consultation_fee DOUBLE PRECISION,
    user_id VARCHAR(36),
    CONSTRAINT fk_doctor_user FOREIGN KEY (user_id) REFERENCES users(id)
);
