package peaksoft.service.impl;

import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import peaksoft.entity.Hospital;
import peaksoft.exceptions.BadRequestException;
import peaksoft.exceptions.NotFoundException;
import peaksoft.repository.HospitalRepository;
import peaksoft.service.HospitalService;

import java.util.List;
@Service
@Transactional
public class HospitalServiceImpl implements HospitalService {
    private final HospitalRepository hospitalRepository;
@Autowired
    public HospitalServiceImpl(HospitalRepository hospitalRepository) {
        this.hospitalRepository = hospitalRepository;
    }

    @Override
    public void save(Hospital hospital) throws RuntimeException {
        try {
            hospitalRepository.save(hospital);
        } catch (BadRequestException e) {
   throw new NotFoundException("(Hospital save)Wrong !");
        }
    }

    @Override

    public List<Hospital> getAllCompanies() throws RuntimeException {
        try {
            return hospitalRepository.getAllCompanies();
        } catch (RuntimeException e) {
            throw new NotFoundException("Hospital don't found");
        }
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
}
