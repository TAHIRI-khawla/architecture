package com.architectures.notificationservice.client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name = "rdv-service", url = "${rdv.service.url}")
public interface AppointmentClient {
    @GetMapping("/api/appointments")
    Object getAllAppointments();

    @GetMapping("/api/appointments/doctor/{id}")
    Object getAppointmentsByDoctor(@PathVariable("id") Long doctorId);
}

