package com.hospital.service;

import com.hospital.entity.Appointment;
import com.hospital.repository.AppointmentRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.List;

@Component
@RequiredArgsConstructor
public class ReminderScheduler {

    private final AppointmentRepository appointmentRepository;
    private final EmailService emailService;

    @Scheduled(fixedRate = 60000) // every 1 minute
    public void sendReminders() {

        LocalDateTime now = LocalDateTime.now();
        LocalDateTime nextHour = now.plusHours(1);

        List<Appointment> appointments =
                appointmentRepository
                        .findByAppointmentTimeBetween(now, nextHour);

        for (Appointment appointment : appointments) {

            emailService.sendAppointmentConfirmation(
                    appointment.getPatient().getEmail(),
                    appointment.getPatient().getName(),
                    appointment.getDoctor().getName(),
                    appointment.getAppointmentTime().toString()
            );
        }
    }
}