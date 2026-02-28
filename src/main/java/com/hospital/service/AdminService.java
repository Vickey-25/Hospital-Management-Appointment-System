
package com.hospital.service;

import com.hospital.repository.AppointmentRepository;  
import lombok.RequiredArgsConstructor;

import java.time.LocalDate;
import java.time.LocalDateTime;

import org.springframework.stereotype.Service;
@Service
@RequiredArgsConstructor
public class AdminService {

    private final AppointmentRepository appointmentRepository;

    public Long getTotalAppointments() {
        return appointmentRepository.countTotalAppointments();
    }

public Long getTodayAppointments() {

    LocalDateTime startOfDay = LocalDate.now().atStartOfDay();
    LocalDateTime endOfDay = LocalDate.now().atTime(23, 59, 59);

    return appointmentRepository
            .countByAppointmentTimeBetween(startOfDay, endOfDay);
}
}