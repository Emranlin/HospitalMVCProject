package peaksoft.repository.impl;

import jakarta.persistence.EntityManager;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import peaksoft.entity.Department;
import peaksoft.entity.Hospital;
import peaksoft.repository.HospitalRepository;

import java.util.List;
@Repository
@Transactional

public class HospitalRepositoryImpl implements HospitalRepository {
    private final EntityManager entityManager;
@Autowired
    public HospitalRepositoryImpl(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    @Override
    public void save(Hospital hospital) {
        entityManager.persist(hospital);

    }

    @Override
    public List<Hospital> getAllCompanies() {
        return entityManager.createQuery("select  h from Hospital h", Hospital.class).getResultList();
    }

    @Override
    public Hospital getCompanyById(Long id) {

    return null;
    }

    @Override
    public void deleteCompany(Long id) {

    }

    @Override
    public void updateCompany(Long id, Hospital newHospital) {

    }

    @Override
    public void addDepartment(Department department) {

    }
}
