package br.com.estefanosantos.service;

import java.util.List;

import br.com.estefanosantos.exceptions.CustomException;
import br.com.estefanosantos.model.PessoaJuridica;

public interface PessoaJuridicaService {
	
	PessoaJuridica salvarPessoaJuridica(PessoaJuridica pessoaJuridica) throws CustomException;
	
	List<PessoaJuridica> buscarPorNome(String nome);
	
	PessoaJuridica buscarPorCnpj(String cnpj);
	
	PessoaJuridica buscarPorInscricaoEstadual(String inscricao);
}
