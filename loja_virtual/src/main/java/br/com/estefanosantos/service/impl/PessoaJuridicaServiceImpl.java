package br.com.estefanosantos.service.impl;

import java.util.Calendar;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import br.com.estefanosantos.dto.CepDto;
import br.com.estefanosantos.exceptions.CustomException;
import br.com.estefanosantos.model.PessoaJuridica;
import br.com.estefanosantos.model.Usuario;
import br.com.estefanosantos.repository.PessoaJuridicaRepository;
import br.com.estefanosantos.repository.UsuarioRepository;
import br.com.estefanosantos.service.ConsumoApis;
import br.com.estefanosantos.service.PessoaJuridicaService;

@Service
public class PessoaJuridicaServiceImpl implements PessoaJuridicaService {

	@Autowired
	private PessoaJuridicaRepository pessoaJuridicaRepository;

	@Autowired
	private UsuarioRepository usuarioRepository;

	@Autowired
	private JdbcTemplate jdbcTemplate;

	@Autowired
	private BCryptPasswordEncoder passwordEncoder;

	@Autowired
	private EmailService emailService;
	
	@Autowired
	private ConsumoApis api;
	

	@Override
	public PessoaJuridica salvarPessoaJuridica(PessoaJuridica pessoaJuridica) throws CustomException {

		PessoaJuridica pj = pessoaJuridicaRepository.existeCnpj(pessoaJuridica.getCnpj());

		if (pj != null) {
			throw new CustomException("Cnpj já cadastrado no sistema.");
		}

		pj = pessoaJuridicaRepository.existeEmail(pessoaJuridica.getEmail());

		if (pj != null) {
			throw new CustomException("Email já cadastrado no sistema.");
		}

		pj = pessoaJuridicaRepository.existeInscricaoEstadual(pessoaJuridica.getInscricaoEstadual());

		if (pj != null) {
			throw new CustomException("Já existe Pessoa Jurídica com inscrição estadual de número "
					+ pessoaJuridica.getInscricaoEstadual());
		}
		
		if (pessoaJuridica.getId() == null || pessoaJuridica.getId() <= 0) {
			for (int i = 0; i < pessoaJuridica.getEnderecos().size(); i++) {
				String cepLimpo = pessoaJuridica.getEnderecos().get(i).getCep().replaceAll("[^0-9]", "");
				CepDto dto = api.consultaCep(cepLimpo);
				
				pessoaJuridica.getEnderecos().get(i).setBairro(dto.getBairro());
				pessoaJuridica.getEnderecos().get(i).setCep(cepLimpo);
				pessoaJuridica.getEnderecos().get(i).setCidade(dto.getLocalidade());
				pessoaJuridica.getEnderecos().get(i).setComplemento(dto.getComplemento());
				pessoaJuridica.getEnderecos().get(i).setRua(dto.getLogradouro());
				pessoaJuridica.getEnderecos().get(i).setUf(dto.getUf());
			}
		}

		for (int i = 0; i < pessoaJuridica.getEnderecos().size(); i++) {
			pessoaJuridica.getEnderecos().get(i).setEmpresa(pessoaJuridica);
			pessoaJuridica.getEnderecos().get(i).setPessoa(pessoaJuridica);
		}

		pj = pessoaJuridicaRepository.save(pessoaJuridica);

		Usuario usuario = usuarioRepository.findUserByPessoa(pj.getId(), pj.getEmail());

		if (usuario == null) {

			String constraint = usuarioRepository.checkConstraintRole();

			if (constraint != null) {
				jdbcTemplate.execute("begin; alter table usuario_role drop constraint " + constraint + "; commit;");
			}

			usuario = new Usuario();
			usuario.setDataAttPassword(Calendar.getInstance().getTime());
			usuario.setEmpresa(pj);
			usuario.setPessoa(pj);
			usuario.setLogin(pj.getEmail());

			String senha = "" + Calendar.getInstance().getTimeInMillis();
			String senhaCript = passwordEncoder.encode(senha);
			usuario.setPassword(senhaCript);

			
			usuario = usuarioRepository.save(usuario);
			usuarioRepository.insereRole(usuario.getId());
			usuarioRepository.insereRole(usuario.getId(), "ADMIN");
			
			StringBuilder mensagem = new StringBuilder();

			mensagem.append("<b>Cadastro efetuado com sucesso!</b><br>");
			mensagem.append("<b>Login: </b>" + pj.getEmail() + "<br>");
			mensagem.append("<b>Senha: </b>").append(senha).append("</br>");
			mensagem.append("Obrigado!");

			try {
				emailService.sendEmailHtml(pj.getEmail(), "Cadastro efetuado com sucesso na loja virtual",
						mensagem.toString());
			} catch (Exception e) {
				e.printStackTrace();
			}

		}

		return pj;
	}

}
