package com.hospital.controller;

import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/test")
public class TestController {

    @GetMapping("/hello")
    public String hello() {
        return "Secure endpoint working!";
    }
}