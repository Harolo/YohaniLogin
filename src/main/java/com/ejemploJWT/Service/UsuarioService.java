package com.ejemploJWT.Service;

import com.ejemploJWT.Dto.RegistroDto;
import org.springframework.http.ResponseEntity;

public interface UsuarioService {
    ResponseEntity<?> listarTodo();
    ResponseEntity<String> guardarUsuario(RegistroDto registroDto);
    ResponseEntity<?> listarUsuario(Long id);
    ResponseEntity<String> editarUsuario(RegistroDto registroDto, Long id);
    ResponseEntity<String> eliminarUsuario(Long id);
}
