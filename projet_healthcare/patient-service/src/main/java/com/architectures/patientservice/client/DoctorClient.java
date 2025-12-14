package com.architectures.patientservice.client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name = "docteur-service", url = "${docteur.service.url}")
public interface DoctorClient {
    @GetMapping("/api/doctors/{id}")
    Object getDoctorById(@PathVariable("id") Long id);

    @GetMapping("/api/doctors")
    Object getAllDoctors();

    @GetMapping("/api/doctors/search")
    Object searchDoctors(@org.springframework.web.bind.annotation.RequestParam(required = false) String specialite);
}

