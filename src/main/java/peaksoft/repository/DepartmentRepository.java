package peaksoft.repository;

import peaksoft.entity.Department;

import java.util.List;

public interface DepartmentRepository {
    void save(Department department);
    List<Department> getAllDepartment(Long id);
    Department getDepartmentById(Long id);
    void deleteDepartment(Long id);
    void updateDepartment( Long id,Department newDepartment);

}
