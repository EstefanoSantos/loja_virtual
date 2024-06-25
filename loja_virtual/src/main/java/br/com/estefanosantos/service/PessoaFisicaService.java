package br.com.estefanosantos.service;

import java.util.List;

import br.com.estefanosantos.exceptions.CustomException;
import br.com.estefanosantos.model.PessoaFisica;

public interface PessoaFisicaService {
	
	PessoaFisica salvarPessoaFisica(PessoaFisica pessoaFisica) throws CustomException;
	
	List<PessoaFisica> buscarPessoasPorNome(String nomeParcial);
}
