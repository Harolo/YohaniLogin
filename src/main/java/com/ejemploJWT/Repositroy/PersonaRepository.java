package com.ejemploJWT.Repositroy;

import com.ejemploJWT.Model.Persona;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface PersonaRepository extends JpaRepository<Persona, Long> {
    Optional<Persona> findByNombreAndApellidos(String nombre, String apellidos);
}
