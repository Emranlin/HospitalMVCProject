package peaksoft.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Entity
@Table(name = "hospitals")
@Setter
@Getter
@NoArgsConstructor

public class Hospital {
    @Id
    @SequenceGenerator(name = "hospital_id_gen",sequenceName = "hospital_id_seq",allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE ,generator = "hospital_id_seq")

    private  Long id;
    private String name;
    private String address;
    @Column(length =1000000 )
    private String logo;
    @OneToMany(mappedBy = "hospital",cascade = {CascadeType.ALL})
    private List<Doctor> doctors;
    @OneToMany(mappedBy = "hospital",cascade = {CascadeType.DETACH,
            CascadeType.REFRESH,
            CascadeType.REMOVE,
            CascadeType.PERSIST,
            CascadeType.MERGE})
    private List<Patient>patients;
    @OneToMany(mappedBy = "hospital",cascade = {CascadeType.DETACH,
            CascadeType.REFRESH,
            CascadeType.REMOVE,
            CascadeType.PERSIST,
            CascadeType.MERGE})
    private List<Department> departments;
    @OneToMany(cascade = {CascadeType.DETACH,
            CascadeType.REFRESH,
            CascadeType.REMOVE,
            CascadeType.PERSIST,
            CascadeType.MERGE})
    private List<Appointment>appointments;
}
