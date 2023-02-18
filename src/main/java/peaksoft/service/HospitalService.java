package peaksoft.service;

import peaksoft.entity.Hospital;

import java.util.List;

public interface HospitalService {
    void save(Hospital hospital);
    List<Hospital> getAllHospitals();
    Hospital getHospitalById(Long id);
    void deleteHospital(Long id);
    void updateHospital( Long id,Hospital newHospital);
}
