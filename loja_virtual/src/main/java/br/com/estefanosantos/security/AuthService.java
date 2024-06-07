package br.com.estefanosantos.security;

import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import br.com.estefanosantos.controller.dto.LoginDto;
import br.com.estefanosantos.model.Usuario;
import br.com.estefanosantos.repository.UsuarioRepository;

@Service
public class AuthService {
	
	private final UsuarioRepository usuarioRepository;
	
	private final BCryptPasswordEncoder encoder;
	
	private final JwtTokenUtil tokenUtil;
	
	
	public AuthService(UsuarioRepository usuarioRepository, BCryptPasswordEncoder encoder, JwtTokenUtil tokenUtil) {
		this.usuarioRepository = usuarioRepository;
		this.encoder = encoder;
		this.tokenUtil = tokenUtil;
		
	}
	
	public String usuarioLogin(LoginDto dto) {
		Usuario user = usuarioRepository.findUserByLogin(dto.login());

		if (user == null || !user.isLoginCorrect(dto, encoder)) {
			throw new BadCredentialsException("");
		}

		UsernamePasswordAuthenticationToken authenticationToken =
				new UsernamePasswordAuthenticationToken(user.getUsername(), user.getPassword(), user.getAuthorities());

		return tokenUtil.generateToken(authenticationToken);
	}
	
	
}
