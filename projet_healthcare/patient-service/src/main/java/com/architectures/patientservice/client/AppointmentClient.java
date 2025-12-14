package com.architectures.patientservice.client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name = "rdv-service", url = "${rdv.service.url}")
public interface AppointmentClient {
    @GetMapping("/api/appointments")
    Object getAllAppointments();
}

