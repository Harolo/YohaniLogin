package com.ejemploJWT.Service;

import com.ejemploJWT.Dto.RegistroDto;
import org.springframework.http.ResponseEntity;

public interface PerspnaServie {

    ResponseEntity<String> guardarPersona(RegistroDto registroDto);
    ResponseEntity<?> listarTodo();
}
