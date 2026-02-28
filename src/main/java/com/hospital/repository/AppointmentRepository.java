package com.hospital.repository;

import com.hospital.entity.Appointment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.time.LocalDateTime;
import java.util.Optional;
import java.util.List;

public interface AppointmentRepository extends JpaRepository<Appointment, String> {

        @Query("SELECT COUNT(a) FROM Appointment a")
Long countTotalAppointments();

Long countByAppointmentTimeBetween(
        LocalDateTime start,
        LocalDateTime end
);

    
 Optional<Appointment> findByDoctor_IdAndAppointmentTime(
        String doctorId,
        LocalDateTime appointmentTime
);

List<Appointment> findByAppointmentTimeBetween(
        LocalDateTime start,
        LocalDateTime end
);
}