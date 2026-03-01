

package com.hospital.controller;

import com.hospital.service.AdminService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/admin")
@RequiredArgsConstructor
@PreAuthorize("hasRole('ADMIN')")
public class AdminController {

    private final AdminService adminService;

    // 🔹 Simple test endpoint (for debugging)
    @GetMapping("/test")
    public String test() {
        return "Admin controller is working";
    }

    // 🔹 Total appointments
    @GetMapping("/analytics/total-appointments")
    public Long totalAppointments() {
        return adminService.getTotalAppointments();
    }

    // 🔹 Today's appointments
    @GetMapping("/analytics/today-appointments")
    public Long todayAppointments() {
        return adminService.getTodayAppointments();
    }

    // 🔹 Combined stats endpoint
    @GetMapping("/stats")
    public AdminStatsResponse getStats() {
        return new AdminStatsResponse(
                adminService.getTotalAppointments(),
                adminService.getTodayAppointments()
        );
    }
}