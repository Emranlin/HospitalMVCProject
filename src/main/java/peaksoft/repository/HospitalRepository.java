package peaksoft.repository;

import peaksoft.entity.Department;
import peaksoft.entity.Hospital;

import java.util.List;

public interface HospitalRepository {
    void save(Hospital hospital);
    List<Hospital> getAllCompanies();
    Hospital getCompanyById(Long id);
    void deleteCompany(Long id);
    void updateCompany( Long id,Hospital newHospital);
    void addDepartment(Department department);
}
