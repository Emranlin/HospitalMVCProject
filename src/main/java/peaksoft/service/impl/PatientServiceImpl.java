package peaksoft.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import peaksoft.entity.Patient;
import peaksoft.exceptions.BadRequestException;
import peaksoft.exceptions.NotFoundException;
import peaksoft.repository.PatientRepository;
import peaksoft.service.PatientService;

import java.util.List;
@Service

public class PatientServiceImpl implements PatientService {
    private final PatientRepository patientRepository;

    @Autowired
    public PatientServiceImpl(PatientRepository patientRepository) {
        this.patientRepository = patientRepository;
    }

    @Override
    public void save(Long hospitalId, Patient patient) throws RuntimeException {
        try {
            patientRepository.save(hospitalId, patient);
        } catch (RuntimeException e) {
            throw new BadRequestException("Patient un correct");
        }
    }

    @Override
    public List<Patient> getAllPatients(Long hosptalId) throws RuntimeException {
        try {
            return patientRepository.getAllPatients(hosptalId);
        } catch (RuntimeException e) {
            throw new NotFoundException("Patient not found");
        }

    }

    @Override
    public Patient getPatientById(Long id) {
        try {
            return patientRepository.getPatientById(id);
        } catch (RuntimeException e) {
            throw new NotFoundException("There is no like this patient");
        }
    }

    @Override
    public void deletePatient(Long id) {
        try {
            patientRepository.deletePatient(id);
        } catch (RuntimeException e) {
            throw new BadRequestException("Patient don't delete");
        }

    }

    @Override
    public void updatePatient(Long id, Patient patient) {
        try {
            patientRepository.updatePatient(id, patient);
        } catch (RuntimeException e) {
            throw new BadRequestException("Patient un correct");
        }
    }

    @Override
    public int countPatient() {
        try {
            return patientRepository.countPatient();
        } catch (RuntimeException e) {
            throw new NotFoundException("Patient is count un correct");
        }
    }
}
