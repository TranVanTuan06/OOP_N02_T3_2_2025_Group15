package com.nocturne.cafemanagerweb.web;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HealthController {

    @GetMapping("/health")
    public String home() {
        return "CafeManager is running ✅";
    }
}
