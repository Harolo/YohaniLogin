package com.ejemploJWT.Exceptions;

public class MensajeError extends RuntimeException {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
 

	private String nombreDelRecurso;
 

	public MensajeError(String nombreDelRecurso) {
		super(String.format("Mensaje:  %s", nombreDelRecurso));
		this.nombreDelRecurso = nombreDelRecurso;

	}

	public String getNombreDelRecurso() {
		return nombreDelRecurso;
	}

	public void setNombreDelRecurso(String nombreDelRecurso) {
		this.nombreDelRecurso = nombreDelRecurso;
	}


}
