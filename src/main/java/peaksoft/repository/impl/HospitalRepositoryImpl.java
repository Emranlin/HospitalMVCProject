package peaksoft.repository.impl;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.transaction.Transactional;

import org.springframework.stereotype.Repository;
import peaksoft.entity.Appointment;
import peaksoft.entity.Department;
import peaksoft.entity.Hospital;
import peaksoft.repository.HospitalRepository;

import java.util.List;
@Repository
@Transactional

public class HospitalRepositoryImpl implements HospitalRepository {
    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public void save(Hospital hospital) {
        entityManager.persist(hospital);

    }

    @Override
    public List<Hospital> getAllHospital() {
        return entityManager.createQuery("select  h from Hospital h", Hospital.class).getResultList();
    }

    @Override
    public Hospital getHospitalById(Long id) {

    return entityManager.find(Hospital.class,id);
    }

    @Override
    public void deleteHospital(Long id) {
       entityManager.remove(entityManager.find(Hospital.class,id));
    }

    @Override
    public void updateHospital(Long id, Hospital newHospital) {
      Hospital hospital1=  entityManager.find(Hospital.class,id);
      hospital1.setId(newHospital.getId());
      hospital1.setName(newHospital.getName());
      hospital1.setAddress(newHospital.getAddress());
      hospital1.setLogo(newHospital.getLogo());
      entityManager.persist(hospital1);

    }



}
