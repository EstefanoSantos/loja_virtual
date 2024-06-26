package br.com.estefanosantos.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

@Service
public class ContagemAcessoApiService {
	
	@Autowired
	private JdbcTemplate jdbcTemplate;
	
	
	public void atualizarContagemBuscaPfNome() {
		jdbcTemplate.execute("begin; update qtd_acessos_end_point set qtd_acessos = qtd_acessos"
				+ " + 1 where nome_end_point = 'END-POINT-NOME-PESSOA-FISICA'; commit;");
	}
	
	public void atualizarContagemBuscaPfCpf() {
		jdbcTemplate.execute("begin; update qtd_acessos_end_point set qtd_acessos = qtd_acessos"
				+ " + 1 where nome_end_point = 'END-POINT-BUSCAR-PF-CPF'; commit;");
	}

}
