package peaksoft.service.impl;

import jakarta.transaction.Transactional;
import org.hibernate.HibernateException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import peaksoft.entity.Department;
import peaksoft.entity.Hospital;
import peaksoft.exceptions.BadRequestException;
import peaksoft.exceptions.NotFoundException;
import peaksoft.repository.DepartmentRepository;
import peaksoft.repository.DoctorRepository;
import peaksoft.repository.HospitalRepository;
import peaksoft.service.DepartmentService;

import java.util.List;
@Service
@Transactional
public class DepartmentServiceImpl implements DepartmentService {
    private final DepartmentRepository departmentRepository;
    private final HospitalRepository hospitalRepository;
    private final DoctorRepository doctorRepository;
   @Autowired
    public DepartmentServiceImpl(DepartmentRepository departmentRepository, HospitalRepository hospitalRepository, DoctorRepository doctorRepository) {
        this.departmentRepository = departmentRepository;
       this.hospitalRepository = hospitalRepository;
       this.doctorRepository = doctorRepository;
   }

    @Override
    @Transactional
    public void save(Long id, Department department) throws RuntimeException {
        Hospital currentHospital = hospitalRepository.getHospitalById(department.getHospitalId());

        for (Department dep : departmentRepository.getAllDepartment(department.getId())) {
            if (!dep.getName().equals(department.getName())) {
                department.setHospital(currentHospital);
                currentHospital.addDepartment(department);
                departmentRepository.save(department);
            } else {
                throw new BadRequestException("Saving the department is wrong");
            }

        }
    }


    @Override
    public List<Department> getAllDepartment( Long id) throws RuntimeException{
       try {
           return departmentRepository.getAllDepartment(id);
       }catch (HibernateException e){
           throw new NotFoundException("Department don't found");
       }

    }

    @Override
    public Department getDepartmentById(Long id) throws RuntimeException{
       try {
           return departmentRepository.getDepartmentById(id);
       }catch (HibernateException e){
           throw new NotFoundException("Department not found");
       }

    }

    @Override
    public void deleteDepartment(Long id) throws RuntimeException {
       try {
           departmentRepository.deleteDepartment(id);
       }catch (HibernateException e){
           throw new HibernateException("Couldn't deleted");
       }


    }

    @Override
    public void updateDepartment(Long id, Department newDepartment) throws RuntimeException{
          try {
              departmentRepository.updateDepartment(id, newDepartment);
          }catch (HibernateException e){
              throw new BadRequestException("department wrong!");
          }
    }


}
