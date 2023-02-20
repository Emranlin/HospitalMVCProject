package peaksoft.service;

import peaksoft.entity.Patient;

import java.util.List;

public interface PatientService {
    void save(Long hospitalId, Patient patient);
    List<Patient> getAllPatients(Long hospitalId);
    Patient getPatientById(Long id);
    void deletePatient(Long id);
    void updatePatient( Long id,Patient patient);
    int countPatient();
}
