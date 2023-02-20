package peaksoft.repository.impl;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Repository;
import peaksoft.entity.Department;
import peaksoft.entity.Doctor;
import peaksoft.entity.Hospital;
import peaksoft.repository.DoctorRepository;

import java.util.List;

@Repository
@Transactional
public class DoctorRepositoryImpl implements DoctorRepository {
    @PersistenceContext
    private EntityManager entityManager;


    @Override
    public void save( Doctor doctor) {
       entityManager.persist(doctor);

    }

    @Override
    public List<Doctor> getAllDoctors(Long id) {
        return entityManager.createQuery("select d from Doctor d join d.hospital l where l.id=:id", Doctor.class)
                .setParameter("id",id)
                .getResultList();
    }

    @Override
    public Doctor getDoctorById(Long id) {
        return entityManager.find(Doctor.class,id);
    }

    @Override
    public void deleteDoctor(Long id) {
        Doctor doctor = entityManager.find(Doctor.class, id);
        entityManager.detach(doctor);
        entityManager.remove(doctor);

    }

    @Override
    public void updateDoctor(Long id, Doctor doctor) {
        Doctor doctor1 = entityManager.find(Doctor.class, id);
        doctor1.setFirstName(doctor.getFirstName());
        doctor1.setLastName(doctor.getLastName());
        doctor1.setPosition(doctor.getPosition());
        doctor1.setEmail(doctor.getEmail());
        doctor1.setPhoto(doctor.getPhoto());
        entityManager.persist(doctor1);
    }

    @Override
    public void addDepartments(Long departmentId, Doctor doctor) {
       Department department= entityManager.createQuery("from Department d where id=:id", Department.class)
                .setParameter("id",departmentId)
                .getSingleResult();
       department.addDoctor(doctor);
       doctor.setDepartments((List<Department>) department);
       entityManager.persist(doctor);

    }

    @Override
    public int countDoctors() {
        int result = entityManager.createNativeQuery("select *,count(first_name) from doctors group by first_name", Doctor.class).executeUpdate();
        return result;
    }
}