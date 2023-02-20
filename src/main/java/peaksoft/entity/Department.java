package peaksoft.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "departments")
@Setter
@Getter
@NoArgsConstructor
public class Department {
    @Id
    @SequenceGenerator(name = "department_id_gen",sequenceName = "department_id-seq",allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE,generator = "department_id_gen")


    private Long id;
    private String name;
    private String photo;
    @Transient
    private Long hospitalId;
//    @Transient
//    private Long doctorId;

    @ManyToMany(mappedBy = "departments",cascade = {CascadeType.DETACH,
            CascadeType.REFRESH,
            CascadeType.PERSIST,
            CascadeType.MERGE},fetch = FetchType.EAGER)

    private List<Doctor> doctors;
    public void addDoctor(Doctor doctor){
        if(doctors==null){
            doctors=new ArrayList<>();
        }
        doctors.add(doctor);
    }
    @ManyToOne(cascade = {CascadeType.REFRESH,
            CascadeType.DETACH,
            CascadeType.PERSIST,
            CascadeType.MERGE})
    private Hospital hospital;


    public Department(String name, String photo) {
        this.name = name;
        this.photo = photo;
    }
}
