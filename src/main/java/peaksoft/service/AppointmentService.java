package peaksoft.service;

import peaksoft.entity.Appointment;

import java.util.Queue;

public interface AppointmentService {
    void save(Long hospitalId, Appointment appointment);
    Queue<Appointment> getAllAppointments(Long id);
    Appointment getAppointmentById(Long id);
    void deleteAppointment(Long id);
    void updateAppointment( Long id,Appointment newAppointment);
}
