package peaksoft.repository.impl;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import peaksoft.entity.Appointment;
import peaksoft.entity.Department;
import peaksoft.entity.Hospital;
import peaksoft.repository.AppointmentRepository;

import java.util.List;
import java.util.Queue;
@Repository
@Transactional

public class AppointmentRepositoryImpl implements AppointmentRepository{
    @PersistenceContext
    private final EntityManager entityManager;

    public AppointmentRepositoryImpl(EntityManager entityManager) {
        this.entityManager = entityManager;
    }


    @Override
    public void save( Appointment appointment) {
        entityManager.persist(appointment);
    }

    @Override
    public List<Appointment> getAllAppointments(Long hospitalId) {
        return  entityManager.createQuery("select a from Hospital h join h.appointments a where h.id = :id",
                Appointment.class)
                .setParameter("id", hospitalId)
                .getResultList();
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
