package com.ejemploJWT.Service;

import com.ejemploJWT.Dto.LoginDto;
import com.ejemploJWT.Security.JWTAuthResponseDto;
import org.springframework.http.ResponseEntity;

import java.text.ParseException;

public interface InicioService {
    ResponseEntity<JWTAuthResponseDto> iniciarSesion(LoginDto loginDto) throws ParseException;
}
