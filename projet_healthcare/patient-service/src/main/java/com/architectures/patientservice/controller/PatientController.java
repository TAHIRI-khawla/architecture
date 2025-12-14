package com.architectures.patientservice.controller;




import com.architectures.patientservice.entity.Patient;
import com.architectures.patientservice.service.PatientService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


import java.net.URI;
import java.util.List;


@RestController
@RequestMapping("/api/patients")
public class PatientController {


    private final PatientService service;


    public PatientController(PatientService service) {
        this.service = service;
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
}