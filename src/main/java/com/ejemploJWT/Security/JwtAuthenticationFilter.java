package com.ejemploJWT.Security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.util.StringUtils;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;


public class JwtAuthenticationFilter extends OncePerRequestFilter{
	
	@Autowired
	private JwtTokenProvider jwtTokenProvider;
	
	@Autowired
	private CustomUserDetailsService customUserDetailsService;
	
	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
			throws ServletException, IOException {
		//Obtenemos el token de la solicitud HTTP
		String token= obtenerLaSolicitud(request);
		//Validamos el token
		if(StringUtils.hasText(token) && jwtTokenProvider.ValidarToken(token)) {
			//Obtenemos el username del token
			String username = jwtTokenProvider.obtenerUsernameDelJWT(token);
			//Cargamos el usuario asociado al token
			UserDetails userDetails = customUserDetailsService.loadUserByUsername(username);
			//Lo autenticamos
			UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(userDetails,null, userDetails.getAuthorities());
			authenticationToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
			
			//Establecemos la seguridad
			SecurityContextHolder.getContext().setAuthentication(authenticationToken);
		}
		filterChain.doFilter(request, response);

	}
	//Bearer token de acceso (Es un formato que nos permite la autorizacion de un conjunto de usuarios, formato que nos permite autorizacion de un usuario)
	private String obtenerLaSolicitud(HttpServletRequest request) {
		String bearerToken = request.getHeader("Authorization");
		if(StringUtils.hasText(bearerToken) && bearerToken.startsWith("Bearer")) {
			return bearerToken.substring(7,bearerToken.length());
		}
		return null;
	}
}
