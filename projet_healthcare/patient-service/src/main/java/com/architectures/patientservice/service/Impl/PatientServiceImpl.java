package com.architectures.patientservice.service.Impl;


import com.architectures.patientservice.entity.Patient;
import com.architectures.patientservice.repository.PatientRepository;
import com.architectures.patientservice.service.PatientService;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PatientServiceImpl implements PatientService {


    private final PatientRepository repository;


    public PatientServiceImpl(PatientRepository repository) {
        this.repository = repository;
    }


    @Override
    public Patient save(Patient patient) {
        return repository.save(patient);
    }


    @Override
    public Patient getById(Long id) {
        return repository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Patient not found with id " + id));
    }


    @Override
    public List<Patient> getAll() {
        return repository.findAll();
    }


    @Override
    public void delete(Long id) {
        repository.deleteById(id);
    }
}