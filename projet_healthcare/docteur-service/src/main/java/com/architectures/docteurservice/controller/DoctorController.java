package com.architectures.docteurservice.controller;

import com.architectures.docteurservice.entity.Doctor;
import com.architectures.docteurservice.service.DoctorService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/api/doctors")
public class DoctorController {

    private final DoctorService service;

    public DoctorController(DoctorService service) {
        this.service = service;
    }

    @GetMapping
    public List<Doctor> getAll() {
        return service.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Doctor> getById(@PathVariable Long id) {
        return service.findById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<Doctor> create(@RequestBody Doctor doctor) {
        Doctor saved = service.save(doctor);
        return ResponseEntity.created(URI.create("/api/doctors/" + saved.getId())).body(saved);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Doctor> update(@PathVariable Long id, @RequestBody Doctor doctor) {
        return service.findById(id)
                .map(existing -> {
                    doctor.setId(existing.getId());
                    Doctor updated = service.save(doctor);
                    return ResponseEntity.ok(updated);
                })
                .orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        service.delete(id);
        return ResponseEntity.noContent().build();
    }

    // ✅ CORRIGÉ
    @GetMapping("/search")
    public List<Doctor> search(@RequestParam(required = false) String specialite) {
        if (specialite != null) {
            return service.findBySpecialite(specialite);
        }
        return service.findAll();
    }
}
