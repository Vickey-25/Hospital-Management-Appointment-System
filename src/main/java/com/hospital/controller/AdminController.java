
package com.hospital.controller;

import com.hospital.service.AdminService;

import lombok.RequiredArgsConstructor;

import org.springframework.security.access.prepost.PreAuthorize;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
@RestController
@RequestMapping("/api/v1/admin")
@RequiredArgsConstructor
@PreAuthorize("hasRole('ADMIN')")
public class AdminController {

    private final AdminService adminService;
    

    @GetMapping("/analytics/total-appointments")
    public Long totalAppointments() {
        return adminService.getTotalAppointments();
    }

    @GetMapping("/analytics/today-appointments")
    public Long todayAppointments() {
        return adminService.getTodayAppointments();
    }
}