package peaksoft.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import peaksoft.entity.Appointment;
import peaksoft.entity.Hospital;
import peaksoft.repository.*;
import peaksoft.service.AppointmentService;

import java.util.Queue;
@Service
public class AppointmentServiceImpl implements AppointmentService {
    private final AppointmentRepository appointmentRepository;
    private final HospitalRepository hospitalRepository;
    private final DoctorRepository doctorRepository;
    private final PatientRepository patientRepository;
    private final DepartmentRepository departmentRepository;
    @Autowired
    public AppointmentServiceImpl(AppointmentRepository appointmentRepository, HospitalRepository hospitalRepository, DoctorRepository doctorRepository, PatientRepository patientRepository, DepartmentRepository departmentRepository) {
        this.appointmentRepository = appointmentRepository;
        this.hospitalRepository = hospitalRepository;
        this.doctorRepository = doctorRepository;
        this.patientRepository = patientRepository;
        this.departmentRepository = departmentRepository;
    }
    @Override
    public void save(Long hospitalId, Appointment appointment) {
        Hospital hospital = hospitalRepository.getHospitalById(hospitalId);
        appointment.setDepartment(departmentRepository.getDepartmentById(appointment.getDepartmentId()));
        appointment.setDoctor(doctorRepository.getDoctorById(appointment.getDoctorId()));
        appointment.setPatient(patientRepository.getPatientById(appointment.getPatientId()));
        hospital.addAppointment(appointment);
        appointment.getPatient().addAppointment(appointment);
        appointment.getDoctor().addAppointment(appointment);
        appointmentRepository.save(appointment);
    }

    @Override
    public Queue<Appointment> getAllAppointments(Long id) {
        return null;
    }

    @Override
    public Appointment getAppointmentById(Long id) {
        return null;
    }

    @Override
    public void deleteAppointment(Long id) {

    }

    @Override
    public void updateAppointment(Long id, Appointment newAppointment) {

    }
}
