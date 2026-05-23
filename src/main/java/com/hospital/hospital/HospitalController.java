package com.hospital.hospital;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class HospitalController {

    @Autowired
    PatientRepository repo;

    @GetMapping("/")
    public String home() {

        return "Hospital Backend Running";
    }

    @PostMapping("/add")
    public Patient addPatient(
            @RequestBody Patient patient
    ) {

        return repo.save(patient);
    }

    @GetMapping("/patients")
    public List<Patient> getPatients() {

        return repo.findAll();
    }
}