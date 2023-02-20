package peaksoft.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

@Entity
@Table(name = "appointments")
@Setter
@Getter
@NoArgsConstructor

public class Appointment {
    @Id
    @SequenceGenerator(name = "appointment_id_gen",sequenceName = "appointment_id_seq",allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE,generator = "appointment_id_gen")

    private Long id;
    private LocalDate localDate;
    @Transient
    private Long departmentId;
    @Transient
    private Long doctorId;
    @Transient
    private Long patientId;
    @ManyToOne(cascade = {CascadeType.DETACH,
            CascadeType.REFRESH,
            CascadeType.PERSIST,
            CascadeType.MERGE},fetch = FetchType.EAGER)
    private Patient patient;
    @ManyToOne(cascade = {CascadeType.DETACH,
            CascadeType.REFRESH,
            CascadeType.PERSIST,
            CascadeType.MERGE},fetch = FetchType.EAGER)
    private Doctor doctor;
    @ManyToOne(cascade = {CascadeType.DETACH,
            CascadeType.REFRESH,
            CascadeType.PERSIST,
            CascadeType.MERGE},fetch = FetchType.EAGER)
    private Department department;

}
