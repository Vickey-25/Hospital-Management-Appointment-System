package com.hospital.repository;

import com.hospital.entity.Appointment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.time.LocalDateTime;
import java.util.Optional;
import java.util.List;

public interface AppointmentRepository extends JpaRepository<Appointment, String> {

    // Count all appointments
    @Query("SELECT COUNT(a) FROM Appointment a")
    Long countTotalAppointments();

    // Count appointments between time range
    Long countByAppointmentTimeBetween(
            LocalDateTime start,
            LocalDateTime end
    );

    // Check doctor availability
    Optional<Appointment> findByDoctor_IdAndAppointmentTime(
            String doctorId,
            LocalDateTime appointmentTime
    );

    // Get appointments between time range
    List<Appointment> findByAppointmentTimeBetween(
            LocalDateTime start,
            LocalDateTime end
    );
}