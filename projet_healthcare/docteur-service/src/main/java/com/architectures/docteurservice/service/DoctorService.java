package com.architectures.docteurservice.service;

import com.architectures.docteurservice.entity.Doctor;
import com.architectures.docteurservice.repository.DoctorRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class DoctorService {

    private final DoctorRepository repo;

    public DoctorService(DoctorRepository repo) {
        this.repo = repo;
    }

    public List<Doctor> findAll() {
        return repo.findAll();
    }

    public Optional<Doctor> findById(Long id) {
        return repo.findById(id);
    }

    public Doctor save(Doctor doctor) {
        return repo.save(doctor);
    }

    public void delete(Long id) {
        repo.deleteById(id);
    }

    // ✅ corrigé
    public List<Doctor> findBySpecialite(String specialite) {
        return repo.findBySpecialite(specialite);
    }


}
