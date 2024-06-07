package br.com.estefanosantos.service.impl;

import java.util.List;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import br.com.estefanosantos.controller.dto.CriarUsuarioDto;
import br.com.estefanosantos.model.PessoaFisica;
import br.com.estefanosantos.model.Role;
import br.com.estefanosantos.model.Usuario;
import br.com.estefanosantos.repository.PessoaFisicaRepository;
import br.com.estefanosantos.repository.RoleRepository;
import br.com.estefanosantos.repository.UsuarioRepository;
import br.com.estefanosantos.service.UsuarioService;
import jakarta.persistence.EntityExistsException;
import jakarta.persistence.EntityNotFoundException;

@Service
public class UsuarioServiceImpl implements UsuarioService {

	private final UsuarioRepository usuarioRepository;
	private final BCryptPasswordEncoder encoder;
	private final RoleRepository roleRepository;
	private final PessoaFisicaRepository pessoaFisicaRepository;

	public UsuarioServiceImpl(UsuarioRepository usuarioRepository, BCryptPasswordEncoder encoder, RoleRepository roleRepository, PessoaFisicaRepository pessoaFisicaRepository) {
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

	



}
