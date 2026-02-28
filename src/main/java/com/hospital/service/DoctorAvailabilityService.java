
package com.hospital.service;

import com.hospital.entity.DoctorAvailability;
import com.hospital.entity.DoctorProfile;
import com.hospital.repository.DoctorAvailabilityRepository;
import com.hospital.repository.DoctorRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class DoctorAvailabilityService {

    private final DoctorAvailabilityRepository availabilityRepository;
    private final DoctorRepository doctorRepository;

    public String createAvailability(String doctorId,
                                     LocalDate date,
                                     LocalTime start,
                                     LocalTime end) {

        if (start.isAfter(end) || start.equals(end)) {
            throw new RuntimeException("Start time must be before end time");
        }

        DoctorProfile doctor = doctorRepository.findById(doctorId)
                .orElseThrow(() -> new RuntimeException("Doctor not found"));

        List<DoctorAvailability> existingSlots =
                availabilityRepository.findByDoctor_IdAndAvailableDate(doctorId, date);

        for (DoctorAvailability slot : existingSlots) {
            boolean overlap =
                    start.isBefore(slot.getEndTime()) &&
                    end.isAfter(slot.getStartTime());

            if (overlap) {
                throw new RuntimeException("Time slot overlaps with existing slot");
            }
        }

        DoctorAvailability availability = DoctorAvailability.builder()
                .id(UUID.randomUUID().toString())
                .availableDate(date)
                .startTime(start)
                .endTime(end)
                .createdAt(LocalDateTime.now())
                .doctor(doctor)
                .build();

        availabilityRepository.save(availability);

        return "Availability created successfully";
    }
}