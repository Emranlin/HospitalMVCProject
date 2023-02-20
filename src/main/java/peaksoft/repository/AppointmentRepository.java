package peaksoft.repository;

import peaksoft.entity.Appointment;
import peaksoft.entity.Hospital;

import java.util.List;
import java.util.Queue;

public interface AppointmentRepository {
    void save(Appointment appointment);
    List<Appointment> getAllAppointments(Long hospitalId);
    Appointment getAppointmentById(Long id);
    void deleteAppointment(Long id);
    void updateAppointment( Long id,Appointment newAppointment);
}
