package com.ejemploJWT.utils;

import org.springframework.http.HttpStatus;

public class BlogAppException extends RuntimeException{
	
	private static final long serialVersionUID = 1L;
	
	private HttpStatus estado;
	
	private String mensaje;
	private String mensaje1;

	public BlogAppException(HttpStatus estado, String mensaje) {
		super();
		this.estado = estado;
		this.mensaje = mensaje;
	}

	public BlogAppException(HttpStatus estado, String mensaje, String mensaje1) {
		super();
		this.estado = estado;
		this.mensaje = mensaje;
		this.mensaje1 = mensaje1;
	}

	public HttpStatus getEstado() {
		return estado;
	}

	public String getMensaje() {
		return mensaje;
	}

	public String getMensaje1() {
		return mensaje1;
	}

	public void setEstado(HttpStatus estado) {
		this.estado = estado;
	}

	public void setMensaje(String mensaje) {
		this.mensaje = mensaje;
	}

	public void setMensaje1(String mensaje1) {
		this.mensaje1 = mensaje1;
	}
}
