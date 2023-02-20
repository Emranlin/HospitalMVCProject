package peaksoft.repository.impl;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Repository;
import peaksoft.entity.Department;
import peaksoft.repository.DepartmentRepository;

import java.util.List;
@Repository
@Transactional
public class DepartmentRepositoryImpl implements DepartmentRepository {
    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public void save(Department department) {
        entityManager.persist(department);
    }

    @Override
    public List<Department> getAllDepartment(Long id) {
        return entityManager.createQuery("select d from Department d join d.hospital l where l.id=:id", Department.class)
                .setParameter("id",id)
                .getResultList();
    }

    @Override
    public Department getDepartmentById(Long id) {
        return entityManager.find(Department.class, id);
    }

    @Override
    public void deleteDepartment(Long id) {
        entityManager.remove(entityManager.find(Department.class, id));

    }

    @Override
    public void updateDepartment(Long id, Department newDepartment) {
        Department department1 = entityManager.find(Department.class, id);
        department1.setName(newDepartment.getName());
        department1.setPhoto(newDepartment.getPhoto());
        entityManager.persist(department1);

    }

}
