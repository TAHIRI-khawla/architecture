package com.architectures.notificationservice.controller;

import com.architectures.notificationservice.entity.Notification;
import com.architectures.notificationservice.Service.NotificationService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@CrossOrigin(origins = "http://localhost:3000")
@RestController
@RequestMapping("/notifications")
public class NotificationController {

    private final NotificationService service;

    public NotificationController(NotificationService service) {
        this.service = service;
    }

    @PostMapping
    public Notification create(@RequestBody Notification notification) {
        return service.save(notification);
    }

    // Endpoints pour communiquer avec les autres services
    @GetMapping("/appointments")
    public ResponseEntity<Object> getAllAppointments() {
        return ResponseEntity.ok(service.getAllAppointments());
    }

    @GetMapping("/appointments/doctor/{doctorId}")
    public ResponseEntity<Object> getAppointmentsByDoctor(@PathVariable Long doctorId) {
        return ResponseEntity.ok(service.getAppointmentsByDoctor(doctorId));
    }

    @GetMapping("/doctors/{doctorId}")
    public ResponseEntity<Object> getDoctorById(@PathVariable Long doctorId) {
        return ResponseEntity.ok(service.getDoctorById(doctorId));
    }

    @GetMapping("/doctors")
    public ResponseEntity<Object> getAllDoctors() {
        return ResponseEntity.ok(service.getAllDoctors());
    }

    @GetMapping("/patients/{patientId}")
    public ResponseEntity<Object> getPatientById(@PathVariable Long patientId) {
        return ResponseEntity.ok(service.getPatientById(patientId));
    }

    @GetMapping("/patients")
    public ResponseEntity<Object> getAllPatients() {
        return ResponseEntity.ok(service.getAllPatients());
    }
}
