package com.ejemploJWT.Service.Implement;

import com.ejemploJWT.Dto.RegistroDto;
import com.ejemploJWT.Model.Persona;
import com.ejemploJWT.Model.Usuario;
import com.ejemploJWT.Repositroy.PersonaRepository;
import com.ejemploJWT.Service.PerspnaServie;
import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PersonaImp implements PerspnaServie {

    private final PersonaRepository personaRepository;
    private final ModelMapper modelMapper;

    public PersonaImp(PersonaRepository personaRepository, ModelMapper modelMapper) {
        this.personaRepository = personaRepository;
        this.modelMapper = modelMapper;
    }

    @Override
    public ResponseEntity<String> guardarPersona(RegistroDto registroDto) {
        try {
            Optional<Persona> persona = personaRepository.findByNombreAndApellidos(registroDto.getNombre(), registroDto.getApellidos());
            if (persona.isPresent()) {
                return new ResponseEntity<String>("ya existe una persona registrada con estos datos", HttpStatus.OK);
            } else {
                personaRepository.save(modelMapper.map(registroDto, Persona.class));
                return new ResponseEntity<String>("registro exitoso", HttpStatus.OK);
            }
        } catch (Exception e) {
            return new ResponseEntity<String>(e.getCause().toString(), HttpStatus.INTERNAL_SERVER_ERROR);
        }

    }

    @Override
    public ResponseEntity<?> listarTodo() {
            try {
                List<Persona> personas = personaRepository.findAll();
                if (personas.isEmpty()) {
                    return new ResponseEntity<String>("no hay datos registrados", HttpStatus.NOT_FOUND);
                } else {
                    return new ResponseEntity<List<Persona>>(personas, HttpStatus.OK);
                }
            } catch (Exception e) {
                return new ResponseEntity<String>(e.getCause().toString(), HttpStatus.INTERNAL_SERVER_ERROR);
            }
        }

}
