package com.hospital.entity;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;

@Entity
@Table(name = "doctor_availability")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class DoctorAvailability {

    @Id
    private String id;

    private LocalDate availableDate;

    private LocalTime startTime;

    private LocalTime endTime;

    private LocalDateTime createdAt;

    @ManyToOne
    @JoinColumn(name = "doctor_id")
    private DoctorProfile doctor;
}
