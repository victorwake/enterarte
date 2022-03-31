package com.enterarte.entity;

<<<<<<< HEAD


=======
>>>>>>> 90793b28618736c89e25562aa498e90ae4766864
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import org.hibernate.annotations.GenericGenerator;

@Entity
<<<<<<< HEAD
@Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
=======
>>>>>>> 90793b28618736c89e25562aa498e90ae4766864
public class Play {

    @Id
    @GeneratedValue(generator = "uuid")
    @GenericGenerator(name = "uuid", strategy = "uuid2")
    private String id;

    private String nombre;
    private String descripcion;
    private Integer duracion;
    
    

    @OneToOne
    private Photo photo;
    
    @OneToOne
    private Location ubicacion;
}
