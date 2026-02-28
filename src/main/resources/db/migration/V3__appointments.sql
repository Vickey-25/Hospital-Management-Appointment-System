CREATE TABLE appointments (
    id VARCHAR(36) PRIMARY KEY,
    appointment_time TIMESTAMP NOT NULL,
    status VARCHAR(30) NOT NULL,
    doctor_id VARCHAR(36) NOT NULL,
    patient_id VARCHAR(36) NOT NULL,
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,

    CONSTRAINT fk_appointment_doctor
        FOREIGN KEY (doctor_id) REFERENCES doctor_profiles(id),

    CONSTRAINT fk_appointment_patient
        FOREIGN KEY (patient_id) REFERENCES users(id)
);
