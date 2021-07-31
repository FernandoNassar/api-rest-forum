package com.demo.project.forum.api.security;

import static com.demo.project.forum.api.security.Constants.EXPIRATION_TIME;
import static com.demo.project.forum.api.security.Constants.SECRET;
import static com.demo.project.forum.api.security.Constants.SIGN_UP_URL;

import java.io.IOException;
import java.util.Date;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.demo.project.forum.api.entities.Usuario;
import com.fasterxml.jackson.databind.ObjectMapper;


public class JWTAuthenticationFilter extends UsernamePasswordAuthenticationFilter {
	
	private AuthenticationManager authenticationManager;
	
	
	public JWTAuthenticationFilter(AuthenticationManager authenticationManager) {
		this.authenticationManager = authenticationManager;
		setFilterProcessesUrl(SIGN_UP_URL);
	}
	
	@Override
	public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response)
			throws AuthenticationException {
		
		try {
			Usuario creds = new ObjectMapper().readValue(request.getInputStream(), Usuario.class);
			
			return authenticationManager
					.authenticate(
							new UsernamePasswordAuthenticationToken(
									creds.getUsername(), 
									creds.getPassword(), 
									creds.getAuthorities()));
		}
		catch(IOException e) { throw new RuntimeException(e); }
		
	}
	
	@Override
	protected void successfulAuthentication(HttpServletRequest request, HttpServletResponse response, FilterChain chain,
			Authentication authResult) throws IOException, ServletException {
		
		String token = JWT.create()
				.withSubject(((Usuario) authResult.getPrincipal()).getUsername())
				.withExpiresAt(new Date(System.currentTimeMillis() + EXPIRATION_TIME))
				.sign(Algorithm.HMAC512(SECRET.getBytes()));
		
		String body = ((Usuario) authResult.getPrincipal()).getUsername() + " " + token;
		
		response.getWriter().write(body);
		response.getWriter().flush();
	}
}















