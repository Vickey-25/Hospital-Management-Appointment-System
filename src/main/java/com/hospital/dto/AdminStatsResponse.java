package com.hospital.dto;

public record AdminStatsResponse(
        Long totalAppointments,
        Long todayAppointments
) {}