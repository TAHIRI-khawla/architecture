package com.architectures.rdvservice.client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name = "patient-service", url = "${patient.service.url}")
public interface PatientClient {
    @GetMapping("/api/patients/{id}")
    Object getPatientById(@PathVariable("id") Long id);

    @GetMapping("/api/patients")
    Object getAllPatients();
}

