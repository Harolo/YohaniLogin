package com.ejemploJWT.Security;


import com.ejemploJWT.Model.Usuario;

public class JWTAuthResponseDto {
	private String tokeDeAcceso;
//	private String tipoDeToken = "Bearer";
//	private Usuario unUsuario;


	public JWTAuthResponseDto(String tokeDeAcceso, String tipoDeToken) {
		super();
		this.tokeDeAcceso = tokeDeAcceso;
//		this.tipoDeToken = tipoDeToken;
	}
	public JWTAuthResponseDto(String tokeDeAcceso, Usuario unUsuario) {
		super();
		this.tokeDeAcceso = tokeDeAcceso; 
//		this.unUsuario=unUsuario;
	}
	
//	public Usuario getUnUsuario() {
//		return unUsuario;
//	}
//	public void setUnUsuario(Usuario unUsuario) {
//		this.unUsuario = unUsuario;
//	}
	public String getTokeDeAcceso() {
		return tokeDeAcceso;
	}
	public void setTokeDeAcceso(String tokeDeAcceso) {
		this.tokeDeAcceso = tokeDeAcceso;
	}
//	public String getTipoDeToken() {
//		return tipoDeToken;
//	}
//	public void setTipoDeToken(String tipoDeToken) {
//		this.tipoDeToken = tipoDeToken;
//	}
	public JWTAuthResponseDto(String tokeDeAcceso) {
		super();
		this.tokeDeAcceso = tokeDeAcceso;
	}
	public JWTAuthResponseDto() {
		super();
	}
	
	
}
