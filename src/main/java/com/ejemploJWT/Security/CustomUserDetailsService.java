package com.ejemploJWT.Security;

import com.ejemploJWT.Model.Usuario;
import com.ejemploJWT.Repositroy.UsuarioRepository;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class CustomUserDetailsService implements UserDetailsService{

	private final UsuarioRepository usuarioRepository;

	public CustomUserDetailsService(UsuarioRepository usuarioRepository) {
		this.usuarioRepository = usuarioRepository;
	}

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		Usuario usuario = usuarioRepository.findByUsuario(username)
				.orElseThrow(()-> new UsernameNotFoundException("Usuario no encontrado con ese username o email: "+username));
		return new User(usuario.getUsuario(),usuario.getContraseña(), mapearRoles(usuario.getRol()));
	}

	private Collection<? extends GrantedAuthority> mapearRoles(Set<Usuario> roles){
		return roles.stream().map(rol->new SimpleGrantedAuthority(rol.toString())).collect(Collectors.toList());
	}

}
