package com.hospital.controller;
//import com.hospital.dto.AdminStatsResponse;

import com.hospital.repository.AppointmentRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
@RequiredArgsConstructor
public class AdminService {

    private final AppointmentRepository appointmentRepository;

    // Total appointments
    public Long getTotalAppointments() {
        return appointmentRepository.countTotalAppointments();
    }

    // Today's appointments
    public Long getTodayAppointments() {

        LocalDateTime startOfDay = LocalDateTime.now()
                .toLocalDate()
                .atStartOfDay();

        LocalDateTime endOfDay = startOfDay.plusDays(1);

        return appointmentRepository
                .countByAppointmentTimeBetween(startOfDay, endOfDay);
    }
}