package com.demo.project.forum.api.security;

import static com.demo.project.forum.api.security.Constants.*;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import com.demo.project.forum.api.services.UsuarioService;


@Configuration
@EnableWebSecurity
public class WebSecurityConfiguration extends WebSecurityConfigurerAdapter {
	
	@Autowired
	private UsuarioService service;
	
	@Autowired
	private UserDetailsServiceImpl userDetailsService;

	@Autowired
	private BCryptPasswordEncoder bCryptEncoder;
	
	
	
	@Override
	protected void configure(HttpSecurity http) throws Exception {

		http.authorizeRequests()
			.antMatchers(HttpMethod.POST, SIGN_UP_URL).permitAll()
			.antMatchers(HttpMethod.GET, TOPICOS).permitAll()
			.antMatchers(HttpMethod.GET, RESPOSTAS).permitAll()
			.antMatchers(HttpMethod.POST, USUARIOS).permitAll()
			.antMatchers(HttpMethod.GET, USUARIOS).permitAll()
			.antMatchers(HttpMethod.POST, TOPICOS).authenticated()
			.antMatchers(HttpMethod.POST, RESPOSTAS).authenticated()
			.antMatchers(HttpMethod.DELETE, "/**").hasRole("MODERADOR")
			.antMatchers(HttpMethod.PUT, "/**").hasRole("MODERADOR")
			.anyRequest().authenticated()
			.and().csrf().disable()
			.addFilter(new JWTAuthenticationFilter(authenticationManager()))
			.addFilter(new JWTAuthorizationFilter(authenticationManager(), service))
			.sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS);
	}
	
	
	
	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {		
		auth.userDetailsService(userDetailsService).passwordEncoder(bCryptEncoder);
	}



}
