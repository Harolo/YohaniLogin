package com.ejemploJWT.Dto;

import com.ejemploJWT.Model.Persona;
import lombok.Data;

import javax.persistence.Column;

@Data
public class RegistroDto {
    private String contraseña;
    private String usuario;
    private String nombre;
    private String apellidos;
    private String direccion;
    private int telefono;

    public Persona getPersona() {
        return null;
    }
}
