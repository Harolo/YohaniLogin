package com.ejemploJWT.Model;


import lombok.Data;

import javax.persistence.*;

@Data
@Entity
@Table(name = "persona")
public class Persona {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private  Long id;

    @Column(name = "per_nombre", length = 150, nullable = false)
    private String nombre;

    @Column(name = "per_apellidos", length = 150, nullable = false)
    private String apellidos;

    @Column(name = "per_direccion", length = 150, nullable = false)
    private String direccion;

    @Column(name = "per_telefono", length = 150, nullable = false)
    private int telefono;

}
