package com.architectures.rdvservice.controller;

import com.architectures.rdvservice.entity.Appointment;
import com.architectures.rdvservice.service.AppointmentService;
import java.util.List;





import org.springframework.web.bind.annotation.*;
@RestController
@RequestMapping("/api/appointments")
public class AppointmentController {


        private final AppointmentService service;

        public AppointmentController(AppointmentService service) {
            this.service = service;
        }

        @PostMapping
        public Appointment create(@RequestBody Appointment appointment) {
            return service.create(appointment);
        }

        @GetMapping
        public List<Appointment> getAll() {
            return service.getAll();
        }

        @GetMapping("/doctor/{id}")
        public List<Appointment> byDoctor(@PathVariable Long id) {
            return service.getByDoctor(id);
        }
}
