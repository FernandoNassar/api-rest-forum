package com.demo.project.forum.api.security;

import static com.demo.project.forum.api.security.Constants.SECRET;
import static com.demo.project.forum.api.security.Constants.TOKEN_PREFIX;

import java.io.IOException;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.TokenExpiredException;
import com.demo.project.forum.api.entities.Usuario;
import com.demo.project.forum.api.services.UsuarioService;


public class JWTAuthorizationFilter extends BasicAuthenticationFilter {
	
	private UsuarioService service;
	
	public JWTAuthorizationFilter(AuthenticationManager authenticationManager, UsuarioService service) {
		super(authenticationManager);
		this.service = service;
	}
	
	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain chain)
			throws IOException, ServletException {
		
		String header = request.getHeader("Authorization");
		
		if(header == null || !header.startsWith("Bearer")) {
			chain.doFilter(request, response);
			return;
		}
		
		try {
			UsernamePasswordAuthenticationToken authentication = getAuthentication(request);
			SecurityContextHolder.getContext().setAuthentication(authentication);
			chain.doFilter(request, response);
		} 
		
		catch(TokenExpiredException e) { response.setStatus(HttpStatus.FORBIDDEN.value()); }
	}
	
	
	
	
	
	private UsernamePasswordAuthenticationToken getAuthentication(HttpServletRequest request) {
		
		String token = request.getHeader("Authorization");
		
		if(token != null) {
			String username = JWT.require(Algorithm.HMAC512(SECRET.getBytes()))
					.build()
					.verify(token.replace(TOKEN_PREFIX, ""))
					.getSubject();

			Usuario usuario = service.findByEmail(username);
			
			if(username != null) return new UsernamePasswordAuthenticationToken(username, null, usuario.getAuthorities());		
		}
		
		return null;
	}

	
}





















