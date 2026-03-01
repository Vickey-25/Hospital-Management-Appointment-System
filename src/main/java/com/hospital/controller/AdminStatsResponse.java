package com.hospital.controller;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class AdminStatsResponse {

    private Long totalAppointments;
    private Long todayAppointments;
}