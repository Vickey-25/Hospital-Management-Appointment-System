
package com.hospital.controller;

import com.hospital.service.DoctorAvailabilityService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.time.LocalTime;

@RestController
@RequestMapping("/api/v1/availability")
@RequiredArgsConstructor
public class DoctorAvailabilityController {

    private final DoctorAvailabilityService service;

    @PostMapping("/create")
    @PreAuthorize("hasRole('ADMIN')")
    public String create(@RequestParam String doctorId,
                         @RequestParam String date,
                         @RequestParam String start,
                         @RequestParam String end) {

        return service.createAvailability(
                doctorId,
                LocalDate.parse(date),
                LocalTime.parse(start),
                LocalTime.parse(end)
        );
    }
}