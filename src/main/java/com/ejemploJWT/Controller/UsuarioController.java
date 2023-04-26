package com.ejemploJWT.Controller;

import com.ejemploJWT.Dto.RegistroDto;
import com.ejemploJWT.Service.PerspnaServie;
import com.ejemploJWT.Service.UsuarioService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
@RestController
@RequestMapping("/api-usuario")
public class UsuarioController {

    private final UsuarioService usuarioService;
    private final PerspnaServie personaService;


    public UsuarioController(UsuarioService usuarioService, PerspnaServie personaService) {
        this.usuarioService = usuarioService;
        this.personaService = personaService;
    }

    @PostMapping("/guardar")
    private ResponseEntity<String> guardarUsuario(@RequestBody RegistroDto registroDto){
        return usuarioService.guardarUsuario(registroDto);
    }

    @GetMapping("/listar-todo")
    private ResponseEntity<?> listarTodo(){
        return personaService.listarTodo();
    }

    @GetMapping("/listar-usuario/{id}")
    private ResponseEntity<?> listraPersona(@PathVariable Long id){
        return usuarioService.listarUsuario(id);
    }

    @PutMapping("/editar-usuario/{id}")
    private ResponseEntity<String> editarUsuario(@RequestBody RegistroDto registroDto, @PathVariable Long id){
        return usuarioService.editarUsuario(registroDto,id);
    }

    @DeleteMapping("/eliminar-usuario/{id}")
    private ResponseEntity<String> eliminarUsuario(@PathVariable Long id){
        return usuarioService.eliminarUsuario(id);
    }
}
