package br.com.estefanosantos.service;

import java.util.List;

import br.com.estefanosantos.exceptions.CustomException;
import br.com.estefanosantos.model.ContaPagar;

public interface ContaPagarService {
	
	void salvarContaPagar(ContaPagar contaPagar) throws CustomException;
	
	void apagarContaPagar(Long id) throws CustomException;
	
	ContaPagar selecionarContaPagar(Long id) throws CustomException;
	
	List<ContaPagar> selecionarContasPagar() throws CustomException;
	
	List<ContaPagar> selecionarContaPagarDesc(String desc) throws CustomException;
	
}
