package br.com.estefanosantos.service.impl;

import java.util.Calendar;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import br.com.estefanosantos.exceptions.CustomException;
import br.com.estefanosantos.model.PessoaFisica;
import br.com.estefanosantos.model.Usuario;
import br.com.estefanosantos.repository.PessoaFisicaRepository;
import br.com.estefanosantos.repository.UsuarioRepository;
import br.com.estefanosantos.service.PessoaFisicaService;

@Service
public class PessoaFisicaServiceImpl implements PessoaFisicaService {

	@Autowired
	PessoaFisicaRepository pessoaFisicaRepository;

	@Autowired
	UsuarioRepository usuarioRepository;

	@Autowired
	JdbcTemplate jdbcTemplate;

	@Autowired
	BCryptPasswordEncoder passwordEncoder;

	@Autowired
	EmailService emailService;

	@Override
	public PessoaFisica salvarPessoaFisica(PessoaFisica pessoaFisica) throws CustomException {

		PessoaFisica pf = pessoaFisicaRepository.existeCpf(pessoaFisica.getCpf());

		if (pf != null) {
			throw new CustomException("CPF já cadastrado no sistema.");
		}

		pf = pessoaFisicaRepository.existeEmail(pessoaFisica.getEmail());

		if (pf != null) {
			throw new CustomException("Email já cadastrado no sistema.");
		}

		for (int i = 0; i < pessoaFisica.getEnderecos().size(); i++) {
			pessoaFisica.getEnderecos().get(i).setPessoa(pessoaFisica);
		}

		pf = pessoaFisicaRepository.save(pessoaFisica);

		Usuario usuario = usuarioRepository.findUserByPessoa(pf.getId(), pf.getEmail());

		if (usuario == null) {

			String constraint = usuarioRepository.checkConstraintRole();

			if (constraint != null) {

				jdbcTemplate.execute("begin; alter table usuario_role drop constraint " + constraint + "; commit;");

			}

			usuario = new Usuario();
			usuario.setDataAttPassword(Calendar.getInstance().getTime());
			usuario.setPessoa(pf);
			usuario.setEmpresa(pf.getEmpresa());
			usuario.setLogin(pf.getEmail());

			String senha = "" + Calendar.getInstance().getTimeInMillis();
			String senhaCriptograda = passwordEncoder.encode(senha);
			usuario.setPassword(senhaCriptograda);

			usuario = usuarioRepository.save(usuario);
			usuarioRepository.insereRole(usuario.getId());

			StringBuilder msg = new StringBuilder();

			msg.append("<b>Cadastro efetuado com sucesso!</b></br>");
			msg.append("<b>Seu login é: </b>").append(usuario.getLogin()).append("</br");
			msg.append("<b>Sua senha é: </b>").append(senha).append("</br>");
			msg.append("<b>Obrigado!</b>");

			try {
				emailService.sendEmailHtml(pf.getEmail(), "Cadastro efetuado com sucesso.", msg.toString());
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		return pf;
	}

}
