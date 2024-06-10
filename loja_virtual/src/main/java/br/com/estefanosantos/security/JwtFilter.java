package br.com.estefanosantos.security;

import java.io.IOException;

import org.springframework.http.HttpStatus;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.oauth2.server.resource.web.BearerTokenAuthenticationEntryPoint;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.stereotype.Component;

import com.fasterxml.jackson.databind.ObjectMapper;

import br.com.estefanosantos.controller.dto.ObjectErroDto;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@Component("jwtAuthEntryPoint")
public class JwtFilter implements AuthenticationEntryPoint {
	
	private static final AuthenticationEntryPoint delegate = new BearerTokenAuthenticationEntryPoint();
	
	private final ObjectMapper mapper;
	
	public JwtFilter(ObjectMapper mapper) {
		this.mapper = mapper;
	}
		
	@Override
	public void commence(HttpServletRequest request, HttpServletResponse response,
			AuthenticationException authException) throws IOException, ServletException {
		JwtFilter.delegate.commence(request, response, authException);
		
		ObjectErroDto erroDto = new ObjectErroDto();
		erroDto.setErro(authException.getMessage());
		erroDto.setStatus(HttpStatus.UNAUTHORIZED.toString());
		
		mapper.writeValue(response.getWriter(), erroDto);
	}

}
