package com.demo.project.forum.api.security;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.demo.project.forum.api.services.UsuarioService;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {
	
	private final UsuarioService usuarioService;
	
	
	public UserDetailsServiceImpl(UsuarioService usuarioService) {
		this.usuarioService = usuarioService;
	}
	
	
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		return usuarioService.findByEmail(username);
	}
	

}
