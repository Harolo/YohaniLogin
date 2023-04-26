package com.ejemploJWT.Service.Implement;

import com.ejemploJWT.Dto.LoginDto;
import com.ejemploJWT.Exceptions.MensajeError;
import com.ejemploJWT.Model.Usuario;
import com.ejemploJWT.Repositroy.UsuarioRepository;
import com.ejemploJWT.Security.JWTAuthResponseDto;
import com.ejemploJWT.Security.JwtTokenProvider;
import com.ejemploJWT.Service.InicioService;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.text.ParseException;

@Service
public class InicioImp implements InicioService {

    private final UsuarioRepository usuarioRepository;
    private final PasswordEncoder passwordEncoder;
    private final AuthenticationManager authenticationManager;
    private final JwtTokenProvider jwtTokenProvider;

    public InicioImp(UsuarioRepository usuarioRepository, PasswordEncoder passwordEncoder, AuthenticationManager authenticationManager, JwtTokenProvider jwtTokenProvider) {
        this.usuarioRepository = usuarioRepository;
        this.passwordEncoder = passwordEncoder;
        this.authenticationManager = authenticationManager;
        this.jwtTokenProvider = jwtTokenProvider;
    }

    @Override
    public ResponseEntity<JWTAuthResponseDto> iniciarSesion(LoginDto loginDto) throws ParseException {
        Usuario user = usuarioRepository.findByUsuario(loginDto.getUsuario()).orElseThrow(() -> new MensajeError("usuario no registrado en el sistema"));

            boolean passStatus = passwordEncoder.matches(loginDto.getContraseña(), user.getContraseña());
            if (passStatus == false) new MensajeError("Contraseña incorrecta");

            Authentication authentication = authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(loginDto.getUsuario(), loginDto.getContraseña())
            );

            SecurityContextHolder.getContext().setAuthentication(authentication);
            String token = jwtTokenProvider.generarToken(authentication);
            user.setContraseña("**********");
            return ResponseEntity.ok(new JWTAuthResponseDto(token));


    }
}
