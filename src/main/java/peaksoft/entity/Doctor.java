package peaksoft.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Entity
@Table(name = "doctors")
@Setter
@Getter
@NoArgsConstructor

public class Doctor {
    @Id
    @SequenceGenerator(name = "doctor_id_gen",sequenceName = "doctor_id_seq",allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE,generator = "doctor_id_gen")

    private Long id;
    @Column(name = "first_name")
    private String firstName;
    @Column(name = "last_name")
    private String lastName;
    private String position;
    private String email;
    private String photo;
    @ManyToMany(cascade = {CascadeType.DETACH,
            CascadeType.REFRESH,
            CascadeType.REMOVE,
            CascadeType.PERSIST,
            CascadeType.MERGE})
    @JoinTable
    private List<Department> departments;
    @OneToMany(mappedBy = "doctor",cascade = {CascadeType.DETACH,
            CascadeType.REFRESH,
            CascadeType.REMOVE,
            CascadeType.PERSIST,
            CascadeType.MERGE})
    private List<Appointment>appointments;
    @ManyToOne(cascade = {CascadeType.DETACH,
    CascadeType.PERSIST,
    CascadeType.REFRESH,
    CascadeType.MERGE})
    private Hospital hospital;
}
