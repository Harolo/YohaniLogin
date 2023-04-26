package com.ejemploJWT.Service.Implement;

import com.ejemploJWT.Dto.RegistroDto;
import com.ejemploJWT.Model.Persona;
import com.ejemploJWT.Model.Usuario;
import com.ejemploJWT.Repositroy.PersonaRepository;
import com.ejemploJWT.Repositroy.UsuarioRepository;
import com.ejemploJWT.Service.PerspnaServie;
import com.ejemploJWT.Service.UsuarioService;
import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.MultiValueMap;

import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class UsuarioImp implements UsuarioService {

    private final PersonaRepository personaRepository;
    private final PasswordEncoder passwordEncoder;
    private final UsuarioRepository usuarioRepository;

    private final PerspnaServie perspnaServie;
    private final ModelMapper modelMapper;

    public UsuarioImp(PersonaRepository personaRepository, PasswordEncoder passwordEncoder, UsuarioRepository usuarioRepository, PerspnaServie perspnaServie, ModelMapper modelMapper) {
        this.personaRepository = personaRepository;
        this.passwordEncoder = passwordEncoder;
        this.usuarioRepository = usuarioRepository;
        this.perspnaServie = perspnaServie;
        this.modelMapper = modelMapper;
    }


    @Override
    public ResponseEntity<?> listarTodo() {

        try {
            List<Usuario> usuarios = usuarioRepository.findAll();
            if (usuarios.isEmpty()) {
                return new ResponseEntity<String>("no hay datos registrados", HttpStatus.NOT_FOUND);
            } else {
                return new ResponseEntity<List<Usuario>>(usuarios, HttpStatus.OK);
            }
        } catch (Exception e) {
            return new ResponseEntity<String>(e.getCause().toString(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @Override
    public ResponseEntity<String> guardarUsuario(RegistroDto registroDto) {

        ResponseEntity<String> persona = perspnaServie.guardarPersona(registroDto);

        try {
            Optional<Persona> persona1 = personaRepository.findByNombreAndApellidos(registroDto.getNombre(), registroDto.getApellidos());
            Boolean usuario = usuarioRepository.existsByUsuario(registroDto.getUsuario());
            if (usuario == true) {
                return new ResponseEntity<String>("ya existe un registro con estos datos", HttpStatus.OK);
            } else {
                Usuario usuario1 = new Usuario();
                usuario1.setUsuario(registroDto.getUsuario());
                usuario1.setContraseña(passwordEncoder.encode(registroDto.getContraseña()));
                usuario1.setPersona(persona1.get());
                usuarioRepository.save(modelMapper.map(usuario1, Usuario.class));
                return new ResponseEntity<String>("registro exitoso", HttpStatus.OK);
            }
        } catch (Exception e) {
            return new ResponseEntity<String>(e.getCause().toString(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @Override
    public ResponseEntity<?> listarUsuario(Long id) {
        try {
            Optional<Usuario> usuario = usuarioRepository.findById(id);
            if (usuario.isPresent()) {
                return new ResponseEntity<Usuario>(usuario.get(), HttpStatus.OK);
            } else {
                return new ResponseEntity<String>("usuario no registrado", HttpStatus.OK);
            }
        } catch (Exception e) {
            return new ResponseEntity<String>(e.getCause().toString(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @Override
    public ResponseEntity<String> editarUsuario(RegistroDto registroDto, Long id) {
        try {
            Optional<Usuario> usuario = usuarioRepository.findById(id);
            if (usuario.isPresent()) {
                Usuario usuarioEncontrado = usuario.get();
                usuarioEncontrado.setUsuario(registroDto.getUsuario());
                usuarioEncontrado.setContraseña(registroDto.getContraseña());
                usuarioEncontrado.setPersona(registroDto.getPersona());
                usuarioRepository.save(usuarioEncontrado);
                return new ResponseEntity<String>("usuario actualizado con exito", HttpStatus.OK);
            } else {
                return new ResponseEntity<String>("usuario no existe", HttpStatus.OK);
            }
        } catch (Exception e) {
            return new ResponseEntity<String>(e.getCause().toString(), HttpStatus.INTERNAL_SERVER_ERROR);

        }

    }

    @Override
    public ResponseEntity<String> eliminarUsuario(Long id) {
        try {
            Optional<Usuario> usuario = usuarioRepository.findById(id);
            if (usuario.isPresent()) {
                usuarioRepository.delete(usuario.get());
                return new ResponseEntity<String>("usuario eliminado con exito", HttpStatus.OK);
            } else {
                return new ResponseEntity<String>("usuario no encontrado", HttpStatus.OK);
            }
        } catch (Exception e) {
            return new ResponseEntity<String>(e.getCause().toString(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
