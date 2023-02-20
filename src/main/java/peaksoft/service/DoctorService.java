package peaksoft.service;

import peaksoft.entity.Doctor;

import java.util.List;

public interface DoctorService {
    void save(Doctor doctor);
    List<Doctor> getAllDoctors(Long id);
    Doctor getDoctorById(Long id);
    void deleteDoctor(Long id);
    void updateDoctor( Long id,Doctor doctor);
    void addDepartments(Long departmentId,Doctor doctor);
    int countDoctors();
}
