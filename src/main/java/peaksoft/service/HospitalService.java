package peaksoft.service;

import peaksoft.entity.Hospital;

import java.util.List;

public interface HospitalService {
    void save(Hospital hospital);
    List<Hospital> getAllCompanies();
    Hospital getCompanyById(Long id);
    void deleteCompany(Long id);
    void updateCompany( Long id,Hospital newHospital);
}
