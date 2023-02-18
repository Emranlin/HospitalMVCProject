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

    public List<Hospital> getAllHospitals() throws RuntimeException {
        try {
            return hospitalRepository.getAllHospital();
        } catch (RuntimeException e) {
            throw new NotFoundException("Hospital don't found");
        }
    }

    @Override
    public Hospital getHospitalById(Long id)throws RuntimeException {
    try {
        return hospitalRepository.getHospitalById(id);
    }catch (RuntimeException e){
        throw new NotFoundException("Hospital not found");
    }

    }

    @Override
    public void deleteHospital(Long id) throws RuntimeException{
    try {
        hospitalRepository.deleteHospital(id);
    }catch (NotFoundException e){
        throw new NotFoundException("Hospital don't deleted");
    }

    }

    @Override
    public void updateHospital(Long id, Hospital newHospital) throws RuntimeException{
    try{
        hospitalRepository.updateHospital(id, newHospital);
    }catch (RuntimeException e){
        throw new NotFoundException("Hospital wrong");
    }

    }
}
