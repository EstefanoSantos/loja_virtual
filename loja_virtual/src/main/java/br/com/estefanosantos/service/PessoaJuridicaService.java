package br.com.estefanosantos.service;

import br.com.estefanosantos.exceptions.CustomException;
import br.com.estefanosantos.model.PessoaJuridica;

public interface PessoaJuridicaService {
	
	PessoaJuridica salvarPessoaJuridica(PessoaJuridica pessoaJuridica) throws CustomException;
}
