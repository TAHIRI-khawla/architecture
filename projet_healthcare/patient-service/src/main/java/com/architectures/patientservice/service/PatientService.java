package com.architectures.patientservice.service;




import com.architectures.patientservice.entity.Patient;


import java.util.List;


public interface PatientService {
    Patient save(Patient patient);
    Patient getById(Long id);
    List<Patient> getAll();
    void delete(Long id);
}
