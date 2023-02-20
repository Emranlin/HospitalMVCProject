package peaksoft.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
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

    @OneToMany(mappedBy = "hospital",cascade = {CascadeType.REFRESH,
            CascadeType.REMOVE,
            CascadeType.PERSIST,
            CascadeType.MERGE})
    private List<Doctor> doctors;
    public void addDoctor(Doctor doctor){
        if(doctors==null){
            doctors=new ArrayList<>();
        }
        doctors.add(doctor);
    }
    @OneToMany(mappedBy = "hospital",cascade = {CascadeType.DETACH,
            CascadeType.REFRESH,
            CascadeType.REMOVE,
            CascadeType.PERSIST,
            CascadeType.MERGE})
    private List<Patient>patients;
    public void addPatient(Patient patient){
        if(patients==null){
            patients=new ArrayList<>();
        }
        patients.add(patient);
    }
    @OneToMany(mappedBy = "hospital",cascade = {CascadeType.DETACH,
            CascadeType.REFRESH,
            CascadeType.REMOVE,
            CascadeType.PERSIST,
            CascadeType.MERGE})
    private List<Department> departments;
    public  void addDepartment(Department department){
        if(departments==null){
            departments=new ArrayList<Department>();
        }
        departments.add(department);
    }
    @OneToMany(cascade = {CascadeType.DETACH,
            CascadeType.REFRESH,
            CascadeType.REMOVE,
            CascadeType.PERSIST,
            CascadeType.MERGE},fetch = FetchType.EAGER)
    private List<Appointment>appointments;
    public void addAppointment(Appointment appointment){
        if (appointments==null){
            appointments=new ArrayList<>();
        }
        appointments.add(appointment);
    }

    public Hospital(String name, String address, String logo) {
        this.name = name;
        this.address = address;
        this.logo = logo;
    }
}
