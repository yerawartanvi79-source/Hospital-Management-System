package com.hospital.hospital;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class PatientController {

    @GetMapping("/Patients")
    public String home() {
        return "Hospital Management System Running";
    }
}