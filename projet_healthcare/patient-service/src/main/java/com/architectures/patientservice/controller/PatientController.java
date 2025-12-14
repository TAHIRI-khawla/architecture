package com.architectures.patientservice.controller;

import com.architectures.patientservice.client.AppointmentClient;
import com.architectures.patientservice.client.DoctorClient;
import com.architectures.patientservice.entity.Patient;
import com.architectures.patientservice.service.PatientService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;

@CrossOrigin(origins = "http://localhost:3000")
@RestController
@RequestMapping("/api/patients")
public class PatientController {

    private final PatientService service;
    private final AppointmentClient appointmentClient;
    private final DoctorClient doctorClient;

    public PatientController(PatientService service,
                            AppointmentClient appointmentClient,
                            DoctorClient doctorClient) {
        this.service = service;
        this.appointmentClient = appointmentClient;
        this.doctorClient = doctorClient;
    }

    @PostMapping
    public ResponseEntity<Patient> create(@RequestBody Patient p) {
        Patient saved = service.save(p);
        return ResponseEntity.created(URI.create("/api/patients/" + saved.getId())).body(saved);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Patient> get(@PathVariable Long id) {
        return ResponseEntity.ok(service.getById(id));
    }

    @GetMapping
    public ResponseEntity<List<Patient>> all() {
        return ResponseEntity.ok(service.getAll());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        service.delete(id);
        return ResponseEntity.noContent().build();
    }

    // Endpoints pour communiquer avec les autres services
    @GetMapping("/appointments")
    public ResponseEntity<Object> getAllAppointments() {
        return ResponseEntity.ok(appointmentClient.getAllAppointments());
    }

    @GetMapping("/doctors/{doctorId}")
    public ResponseEntity<Object> getDoctorById(@PathVariable Long doctorId) {
        return ResponseEntity.ok(doctorClient.getDoctorById(doctorId));
    }

    @GetMapping("/doctors")
    public ResponseEntity<Object> getAllDoctors() {
        return ResponseEntity.ok(doctorClient.getAllDoctors());
    }

    @GetMapping("/doctors/search")
    public ResponseEntity<Object> searchDoctors(@RequestParam(required = false) String specialite) {
        return ResponseEntity.ok(doctorClient.searchDoctors(specialite));
    }
}