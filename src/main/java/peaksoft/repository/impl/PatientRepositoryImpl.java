package peaksoft.repository.impl;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Repository;
import peaksoft.entity.Hospital;
import peaksoft.entity.Patient;
import peaksoft.repository.PatientRepository;

import java.util.List;
@Repository
@Transactional
public class PatientRepositoryImpl implements PatientRepository {
    @PersistenceContext
    private  EntityManager entityManager;
    @Override
    public void save(Long hospitalId, Patient patient) {
        Hospital hospital= entityManager.createQuery("from Hospital h where h.id=:id ", Hospital.class)
                .setParameter("id", hospitalId).getSingleResult();
        hospital.addPatient(patient);
        patient.setHospital(hospital);
        entityManager.persist(patient);

    }

    @Override
    public List<Patient> getAllPatients(Long hospitalId) {
        return entityManager
                .createQuery("from Patient p join p.hospital h where h.id = :id", Patient.class)
                .setParameter("id", hospitalId)
                .getResultList();
    }

    @Override
    public Patient getPatientById(Long id) {
        return entityManager.find(Patient.class,id);
    }

    @Override
    public void deletePatient(Long id) {
        Patient patient = entityManager.find(Patient.class, id);
        entityManager.detach(patient);
        entityManager.remove(patient);

    }

    @Override
    public void updatePatient(Long id, Patient patient) {
        Patient patient1 = entityManager.find(Patient.class, id);
        patient1.setFirsName(patient.getFirsName());
        patient1.setLastName(patient.getLastName());
        patient1.setEmail(patient.getEmail());
        patient1.setGender(patient.getGender());
        patient1.setPhoneNumber(patient.getPhoneNumber());
        entityManager.persist(patient1);

    }

    @Override
    public int countPatient() {
        int result = entityManager.createNativeQuery("select *,count(first_name) from patients group by first_name", Patient.class).executeUpdate();
        return result;
    }
}
