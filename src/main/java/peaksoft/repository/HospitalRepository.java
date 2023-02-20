package peaksoft.repository;

import peaksoft.entity.Appointment;
import peaksoft.entity.Department;
import peaksoft.entity.Hospital;

import java.util.List;

public interface HospitalRepository {
    void save(Hospital hospital);
    List<Hospital> getAllHospital();
    Hospital getHospitalById(Long id);
    void deleteHospital(Long id);
    void updateHospital( Long id,Hospital newHospital);

}
