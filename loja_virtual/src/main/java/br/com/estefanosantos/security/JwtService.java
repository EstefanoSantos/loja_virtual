package br.com.estefanosantos.security;

import java.io.IOException;
import java.time.Instant;
import java.time.ZoneId;
import java.time.ZonedDateTime;

import org.springframework.stereotype.Service;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTCreationException;
import com.auth0.jwt.exceptions.JWTVerificationException;

import br.com.estefanosantos.model.Usuario;
import jakarta.servlet.http.HttpServletResponse;

@Service
public class JwtService {
	
	private static final String SECRETE_KEY = "4Z^XrroxR@dWxqf$mTTKwW$!@#qGr4P";
	
	private static final String ISSUER = "loja_virtual_api";	
	
	public String generateToken(Usuario usuario) throws IOException {
		
		try {
			Algorithm algoritmo = Algorithm.HMAC256(SECRETE_KEY);	
			
			return JWT.create()
							.withIssuer(ISSUER)
							.withIssuedAt(creationTime())
							.withExpiresAt(expirationDate())
							.withSubject(usuario.getUsername())
							.sign(algoritmo);
		
		} catch (JWTCreationException e) {
			throw new JWTCreationException("Erro ao gerar token", e);
		}
	}
	
	public String getSubjectFromToken(String token) {
		
		try {
			Algorithm algoritmo = Algorithm.HMAC256(SECRETE_KEY);
			
			String toker= JWT.require(algoritmo)
					.withIssuer(ISSUER)
					.build()
					.verify(token)
					.getSubject();
			
			System.out.print("Token gerado");
			
			return token;
			
		} catch (JWTVerificationException e) {
			throw new JWTVerificationException("Token inváldo.", e);
		}
	}
	
	private void liberacaoCors(HttpServletResponse response) {
		
		if (response.getHeader("Access-Control-Allow-Origin") == null) {
			response.addHeader("Access-Control-Allow-Origin", "*");
		}
		
		if (response.getHeader("Access-Control-Allow-Headers") == null) {
			response.addHeader("Access-Control-Allow-Headers", "*");
		}
		
		if (response.getHeader("Access-Control-Request-Headers") == null) {
			response.addHeader("Access-Control-Request-Headers", "*");
		}
		
		if (response.getHeader("Access-Control-Allow-Methods") == null) {
			response.addHeader("Access-Control-Allow-Methods", "*");
		}
	}
	
	private Instant creationTime() {
		return ZonedDateTime.now(ZoneId.of("America/Sao_Paulo")).toInstant();
	}
	
	private Instant expirationDate() {
		return ZonedDateTime.now(ZoneId.of("America/Sao_Paulo")).plusHours(4).toInstant();
	}
	
	

}
