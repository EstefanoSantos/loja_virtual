package br.com.estefanosantos.service.impl;

import java.util.List;

import br.com.estefanosantos.controller.dto.CriarUsuarioDto;
import br.com.estefanosantos.model.PessoaFisica;
import br.com.estefanosantos.model.Role;
import br.com.estefanosantos.repository.PessoaFisicaRepository;
import br.com.estefanosantos.repository.RoleRepository;
import jakarta.persistence.EntityExistsException;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import br.com.estefanosantos.controller.dto.LoginDto;
import br.com.estefanosantos.model.Usuario;
import br.com.estefanosantos.repository.UsuarioRepository;
import br.com.estefanosantos.security.JwtService;
import br.com.estefanosantos.service.UsuarioService;

@Service
public class UsuarioServiceImpl implements UsuarioService {

	private final JwtService jwtService;
	private final UsuarioRepository usuarioRepository;
	private final BCryptPasswordEncoder encoder;
	private final RoleRepository roleRepository;
	private final PessoaFisicaRepository pessoaFisicaRepository;

	public UsuarioServiceImpl(JwtService jwtService, UsuarioRepository usuarioRepository, BCryptPasswordEncoder encoder, RoleRepository roleRepository, PessoaFisicaRepository pessoaFisicaRepository) {
		this.jwtService = jwtService;
		this.usuarioRepository = usuarioRepository;
		this.encoder = encoder;
        this.roleRepository = roleRepository;
        this.pessoaFisicaRepository = pessoaFisicaRepository;
    }

	@Override
	public Usuario novoUsuario(CriarUsuarioDto dto) {
		Usuario user = usuarioRepository.findUserByLogin(dto.login());

		if (user != null) {
			throw new EntityExistsException("Login já existe.");
		}

		if (dto.pessoaFisica() == null) {
			throw new IllegalArgumentException("Id da pessoa física não pode ser nulo");
		}

		PessoaFisica pessoaFisica = pessoaFisicaRepository.findById(dto.pessoaFisica())
				.orElseThrow(() -> new EntityNotFoundException("Pessoa Física não encontrada"));

		List<Role> roles = roleRepository.buscarPorDesc("USER");
		Usuario usuario = new Usuario();
		usuario.setLogin(dto.login());
		usuario.setPassword(encoder.encode(dto.password()));
		usuario.setData_att_password(dto.dataAttPassword());
		usuario.setPessoa(pessoaFisica);
		usuario.setRoles(roles);

		return usuarioRepository.save(usuario);
	}

	@Override
	public String usuarioLogin(LoginDto dto) {
		Usuario user = usuarioRepository.findUserByLogin(dto.login());

		if (user == null || !user.isLoginCorrect(dto, encoder)) {
			throw new BadCredentialsException("User or password invalid!");
		}

		UsernamePasswordAuthenticationToken authenticationToken =
				new UsernamePasswordAuthenticationToken(user.getUsername(), user.getPassword(), user.getAuthorities());

		return jwtService.generateToken(authenticationToken);
	}



}
