package br.com.estefanosantos.service.impl;

import java.util.Calendar;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import br.com.estefanosantos.exceptions.CustomException;
import br.com.estefanosantos.model.PessoaJuridica;
import br.com.estefanosantos.model.Usuario;
import br.com.estefanosantos.repository.PessoaJuridicaRepository;
import br.com.estefanosantos.repository.UsuarioRepository;
import br.com.estefanosantos.service.PessoaJuridicaService;

@Service
public class PessoaJuridicaServiceImpl implements PessoaJuridicaService{
	
	@Autowired
	private PessoaJuridicaRepository pessoaJuridicaRepository;
	
	@Autowired
	private UsuarioRepository usuarioRepository;
	
	@Autowired
	private JdbcTemplate jdbcTemplate;
	
	@Autowired
	private BCryptPasswordEncoder passwordEncoder;

	@Override
	public PessoaJuridica salvarPessoaJuridica(PessoaJuridica pessoaJuridica) throws CustomException {
		
		PessoaJuridica pj = pessoaJuridicaRepository.findByCnpj(pessoaJuridica.getCnpj());
		
		if (pj != null) {
			throw new CustomException("Já existe CNPJ cadastrado com o número: " + pj.getCnpj());
		}
			
		pj = pessoaJuridicaRepository.save(pessoaJuridica);
		
		Usuario usuario = usuarioRepository.findUserByPessoa(pj.getId(), pj.getEmail());
		
		if (usuario == null) {
			
			String constraint = usuarioRepository.checkConstraintRole();
			
			if (constraint != null) {
				jdbcTemplate.execute("begin; alter table usuario_role drop constraint " + constraint + "; commit;");
			}
			
			usuario = new Usuario();
			usuario.setData_att_password(Calendar.getInstance().getTime());
			usuario.setEmpresa(pj);
			usuario.setPessoa(pj);
			usuario.setLogin(pj.getEmail());
			
			String senha = "" + Calendar.getInstance().getTimeInMillis();
			String senhaCript = passwordEncoder.encode(senha);
			usuario.setPassword(senhaCript);
			
			usuario = usuarioRepository.save(usuario);
			
		}
		
		return pj;
	}

}
