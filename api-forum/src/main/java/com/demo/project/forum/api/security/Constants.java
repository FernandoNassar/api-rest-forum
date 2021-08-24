package com.demo.project.forum.api.security;

public interface Constants {
	String SECRET = "SECRET_KEY";
	long EXPIRATION_TIME = 9000_000; 
	String TOKEN_PREFIX = "Bearer ";
	String HEADER_STRING = "Authorization";
	String SIGN_UP_URL = "/login";
	String TOPICOS = "/topicos/**";
	String USUARIOS = "/usuarios/**";
	String RESPOSTAS = "/respostas/**";
}
