package com.enterarte.entity;

import com.enterarte.enums.Role;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.GenericGenerator;



@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class Customer {
    
 @Id
    @GeneratedValue(generator = "uuid")
    @GenericGenerator(name = "uuid", strategy = "uuid2")
    private String id;

    private String nombre;
    private String apellido;
    @EqualsAndHashCode.Include
    private String dni;
    private String numeroTelefono;
    @EqualsAndHashCode.Include
    private String email;
    private String clave;

    @Enumerated(EnumType.STRING)
    private Role role;

    private Boolean active;
    
    @OneToOne
    private Photo photo;
    
    public String getFullName(){
        return this.nombre + " " + this.apellido;
    }

}