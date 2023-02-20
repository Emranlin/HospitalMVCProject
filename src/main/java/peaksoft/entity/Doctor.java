package peaksoft.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
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
    @Column(length = 10000000)
    private String photo;
    @Transient
    private Long hospitalId;
    @Transient
    private Long departmentId;
    @ManyToMany(cascade = {CascadeType.DETACH,
            CascadeType.REFRESH,
            CascadeType.PERSIST,
            CascadeType.MERGE})
    @JoinTable
    private List<Department> departments;
    public void addDepartment(Department department){
        if(departments==null){
            departments=new ArrayList<>();
        }
        departments.add(department);
    }
    @OneToMany(mappedBy = "doctor",cascade = {CascadeType.DETACH,
            CascadeType.REFRESH,
            CascadeType.PERSIST,
            CascadeType.MERGE,
    CascadeType.REMOVE},fetch = FetchType.EAGER)
    private List<Appointment>appointments;
    public void addAppointment(Appointment appointment){
        if(appointments==null){
            appointments=new ArrayList<>();
        }
        appointments.add(appointment);
    }
    @ManyToOne(cascade = {
            CascadeType.DETACH,
    CascadeType.PERSIST,
    CascadeType.REFRESH,
    CascadeType.MERGE})
    private Hospital hospital;


    public Doctor(String firstName, String lastName, String position, String email, String photo) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.position = position;
        this.email = email;
        this.photo = photo;
    }
}
