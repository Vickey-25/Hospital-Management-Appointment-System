package com.hospital.controller;

import com.hospital.service.AppointmentService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;

@RestController
@RequestMapping("/api/v1/appointments")
@RequiredArgsConstructor
public class AppointmentController {

    private final AppointmentService appointmentService;

    @PostMapping("/book")
    @PreAuthorize("hasRole('PATIENT')")
    public String book(@RequestParam String doctorId,
                       @RequestParam String patientEmail,
                       @RequestParam String dateTime) {

        return appointmentService.bookAppointment(
                doctorId,
                patientEmail,
                LocalDateTime.parse(dateTime)
        );
    }
}
