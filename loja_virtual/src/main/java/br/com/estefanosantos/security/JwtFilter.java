package br.com.estefanosantos.security;

import java.io.IOException;

import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import br.com.estefanosantos.model.Usuario;
import br.com.estefanosantos.repository.UsuarioRepository;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@Component
public class JwtFilter extends  OncePerRequestFilter {
	
	private final JwtService jwtService;
	
	private final UsuarioRepository usuarioRepository;
	
	public JwtFilter(JwtService jwtService, UsuarioRepository usuarioRepository) {
		this.jwtService = jwtService;
		this.usuarioRepository = usuarioRepository;
	}
	
	@Override
	public void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws IOException, ServletException {	
		
			String token = recoveryToken(request);
			
			if (token != null) {
				String subject = jwtService.getSubjectFromToken(token);
				Usuario user = usuarioRepository.findUserByLogin(subject);
				
				if (user != null) {
					Authentication authentication = new UsernamePasswordAuthenticationToken(user.getUsername(), null, user.getAuthorities());
					
					SecurityContextHolder.getContext().setAuthentication(authentication);
				}								
			}
			filterChain.doFilter(request, response);
		} 
		
	
	
	private String recoveryToken(HttpServletRequest request) {
		String authorizationHeader = request.getHeader("Authorization");
		
		if (authorizationHeader != null) {
			return authorizationHeader.replace("Bearer ", "");
		} 
		
		return null;
	}
	
	
}
