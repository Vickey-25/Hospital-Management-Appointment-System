
package com.hospital.repository;

import com.hospital.entity.DoctorAvailability;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDate;
import java.util.List;

public interface DoctorAvailabilityRepository
        extends JpaRepository<DoctorAvailability, String> {

    List<DoctorAvailability> findByDoctor_IdAndAvailableDate(
            String doctorId,
            LocalDate date
    );
}