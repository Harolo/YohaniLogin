package com.ejemploJWT.Model;


import lombok.Data;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;


@Data
@Entity
@Table(name = "usuario")
public class Usuario {

   @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "usu_nombre", length = 150, nullable = false)
    private String usuario;

    @Column(name = "usu_contraseña", length = 150, nullable = false)
    private String contraseña;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "persona_id", referencedColumnName = "id")
    private Persona persona;

 @ManyToMany(fetch = FetchType.EAGER)
 private Set<Usuario> rol = new HashSet<>();


 /*@Override
 public String toString() {
  return "Usuario{" +
          "id=" + id +
          ", usuario='" + usuario + '\'' +
          ", contraseña='" + contraseña + '\'' +
          ", persona=" + persona +
          ", rol=" + rol +
          '}';
 }*/
}
