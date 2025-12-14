package com.architectures.rdvservice.controller;

import com.architectures.rdvservice.client.DoctorClient;
import com.architectures.rdvservice.client.PatientClient;
import com.architectures.rdvservice.entity.Appointment;
import com.architectures.rdvservice.service.AppointmentService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = "http://localhost:3000")
@RestController
@RequestMapping("/api/appointments")
public class AppointmentController {

    private final AppointmentService service;
    private final DoctorClient doctorClient;
    private final PatientClient patientClient;

    public AppointmentController(AppointmentService service,
                               DoctorClient doctorClient,
                               PatientClient patientClient) {
        this.service = service;
        this.doctorClient = doctorClient;
        this.patientClient = patientClient;
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

    // Endpoints pour communiquer avec les autres services
    @GetMapping("/doctors/{doctorId}")
    public ResponseEntity<Object> getDoctorById(@PathVariable Long doctorId) {
        return ResponseEntity.ok(doctorClient.getDoctorById(doctorId));
    }

    @GetMapping("/patients/{patientId}")
    public ResponseEntity<Object> getPatientById(@PathVariable Long patientId) {
        return ResponseEntity.ok(patientClient.getPatientById(patientId));
    }

    @GetMapping("/patients")
    public ResponseEntity<Object> getAllPatients() {
        return ResponseEntity.ok(patientClient.getAllPatients());
    }
}
