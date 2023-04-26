package com.ejemploJWT.Controller;

import com.ejemploJWT.Dto.LoginDto;
import com.ejemploJWT.Security.JWTAuthResponseDto;
import com.ejemploJWT.Service.InicioService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.text.ParseException;

@RestController
@RequestMapping("/api-inicio")
public class InicioController {

    private final InicioService inicioService;

    public InicioController(InicioService inicioService) {
        this.inicioService = inicioService;
    }

    @PostMapping( "/iniciar-seison")
    private ResponseEntity<JWTAuthResponseDto> iniciar(@RequestBody LoginDto loginDto) throws ParseException {
        return inicioService.iniciarSesion(loginDto);
    }
}
