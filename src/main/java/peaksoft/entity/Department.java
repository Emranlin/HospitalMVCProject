package peaksoft.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

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
    @ManyToMany(mappedBy = "departments",cascade = {CascadeType.DETACH,
            CascadeType.REFRESH,
            CascadeType.REMOVE,
            CascadeType.PERSIST,
            CascadeType.MERGE})

    private List<Doctor> doctors;
    @ManyToOne
    private Hospital hospital;

}
