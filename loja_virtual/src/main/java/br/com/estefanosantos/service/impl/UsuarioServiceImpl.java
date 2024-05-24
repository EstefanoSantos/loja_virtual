package br.com.estefanosantos.service.impl;

import java.io.IOException;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import br.com.estefanosantos.controller.dto.LoginDto;
import br.com.estefanosantos.model.Usuario;
import br.com.estefanosantos.repository.UsuarioRepository;
import br.com.estefanosantos.security.JwtService;
import br.com.estefanosantos.service.UsuarioService;

@Service
public class UsuarioServiceImpl implements UsuarioService {
	
	private final AuthenticationManager authenticationManager;
	
	private final UsuarioRepository usuarioRepository;
	
	private final JwtService jwtService;
	
	private final BCryptPasswordEncoder encoder;
				
	public UsuarioServiceImpl(UsuarioRepository usuarioRepository, BCryptPasswordEncoder encoder, JwtService jwtService) {
		this.authenticationManager = null;
		this.usuarioRepository = usuarioRepository;
		this.jwtService = jwtService;
		this.encoder = encoder;
	}

	@Override
	public String userLogin(LoginDto dto) throws IOException {		
		
			Usuario isUser = usuarioRepository.findUserByLogin(dto.login());
			
			if (isUser == null || !isUser.isLoginCorrect(dto, encoder)) {
				throw new BadCredentialsException("User invalid or doesn't exists");						
			}
			
			UsernamePasswordAuthenticationToken authenticationToken =
					new UsernamePasswordAuthenticationToken(isUser.getUsername(), isUser.getPassword(), isUser.getAuthorities());
			
			Authentication authentication = authenticationManager.authenticate(authenticationToken);
			
			return jwtService.generateToken((Usuario)authentication.getPrincipal());	
		
	}



}
