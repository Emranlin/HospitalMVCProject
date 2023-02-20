package peaksoft.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import peaksoft.enums.Gender;

import java.util.ArrayList;
import java.util.List;



@Entity
@Table(name="patients")
@Setter
@Getter
@NoArgsConstructor
public class Patient {
    @Id
    @SequenceGenerator(name = "patient_id_gen",sequenceName = "patient_id_seq",allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE,generator = "patient_id_gen")

    private Long id;
    @Column(name = "first_name")
    private String firsName;
    @Column(name = "last_name")
    private String lastName;
    @Column(name = "phone_number")
    private String phoneNumber;
    @Enumerated(EnumType.STRING)
    private Gender gender;
    private String email;
    @Transient
    private Long hospitalId;
    @ManyToOne(cascade = {CascadeType.DETACH,
            CascadeType.REFRESH,
            CascadeType.REMOVE,
            CascadeType.PERSIST,
            CascadeType.MERGE})
    private Hospital hospital;
    @OneToMany(mappedBy ="patient", cascade = {CascadeType.DETACH,
            CascadeType.REFRESH,
            CascadeType.REMOVE,
            CascadeType.PERSIST,
            CascadeType.MERGE},fetch = FetchType.EAGER)
    private List<Appointment> appointments;
    public void addAppointment(Appointment appointment){
        if(appointments==null){
            appointments=new ArrayList<>();
        }
        appointments.add(appointment);
    }
}
