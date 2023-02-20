package peaksoft.service.impl;

import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;
import peaksoft.entity.Doctor;
import peaksoft.entity.Hospital;
import peaksoft.exceptions.BadRequestException;
import peaksoft.exceptions.NotFoundException;
import peaksoft.repository.DoctorRepository;
import peaksoft.repository.HospitalRepository;
import peaksoft.service.DoctorService;

import java.util.List;

@Service
@Transactional
public class DoctorServiceImpl implements DoctorService {
    private final DoctorRepository doctorRepository;
    private final HospitalRepository hospitalRepository;


    public DoctorServiceImpl(DoctorRepository doctorRepository, HospitalRepository hospitalRepository) {
        this.doctorRepository = doctorRepository;
        this.hospitalRepository = hospitalRepository;
    }

    @Override
    public void save( Doctor doctor)  {
        Hospital currentHospital = hospitalRepository.getHospitalById(doctor.getHospitalId());
        doctor.setHospital(currentHospital);
        currentHospital.addDoctor(doctor);
        doctorRepository.save(doctor);


    }

    @Override
    public List<Doctor> getAllDoctors(Long id) throws RuntimeException {
        try {
            return doctorRepository.getAllDoctors(id);
        } catch (RuntimeException e) {
            throw new NotFoundException("Doctor is empty");
        }

    }

    @Override
    public Doctor getDoctorById(Long id) throws RuntimeException {
        try {
            return doctorRepository.getDoctorById(id);
        } catch (RuntimeException e) {
            throw new NotFoundException("Doctor is not found");
        }

    }

    @Override
    public void deleteDoctor(Long id) throws RuntimeException {
        try {
            doctorRepository.deleteDoctor(id);
        } catch (RuntimeException e) {
            throw new BadRequestException("Doctor wrong");
        }

    }

    @Override
    public void updateDoctor(Long id, Doctor doctor) throws RuntimeException {
        try {
            doctorRepository.updateDoctor(id, doctor);
        } catch (RuntimeException e) {
            throw new BadRequestException("Doctor error");
        }


    }

    @Override
    public void addDepartments(Long departmentId, Doctor doctor) {
        try {
            doctorRepository.addDepartments(departmentId, doctor);
        } catch (RuntimeException e) {
            throw new BadRequestException("Department assigning error");
        }
    }

    @Override
    public int countDoctors() {
        try {
            return doctorRepository.countDoctors();
        } catch (RuntimeException e) {
            throw new RuntimeException("Un correct counting");
        }
    }
}
