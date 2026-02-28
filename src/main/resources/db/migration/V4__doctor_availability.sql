CREATE TABLE doctor_availability (
    id VARCHAR(255) PRIMARY KEY,
    doctor_id VARCHAR(255) NOT NULL,
    available_date DATE NOT NULL,
    start_time TIME NOT NULL,
    end_time TIME NOT NULL,
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,

    CONSTRAINT fk_availability_doctor
        FOREIGN KEY (doctor_id)
        REFERENCES doctor_profiles(id)
        ON DELETE CASCADE
);
