package com.hospital.entity;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Table(name = "appointments")
public class Appointment {

    @Id
    private String id;

    @Enumerated(EnumType.STRING)
    private AppointmentStatus status;

    private LocalDateTime appointmentTime;

    private LocalDateTime createdAt;

    @ManyToOne
    @JoinColumn(name = "doctor_id")
    private DoctorProfile doctor;

    @ManyToOne
    @JoinColumn(name = "patient_id")
    private User patient;
}