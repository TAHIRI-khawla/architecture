package com.architectures.notificationservice.client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name = "docteur-service", url = "${docteur.service.url}")
public interface DoctorClient {
    @GetMapping("/api/doctors/{id}")
    Object getDoctorById(@PathVariable("id") Long id);

    @GetMapping("/api/doctors")
    Object getAllDoctors();
}

