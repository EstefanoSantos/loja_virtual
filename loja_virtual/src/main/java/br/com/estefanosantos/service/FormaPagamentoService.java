package br.com.estefanosantos.service;

import br.com.estefanosantos.exceptions.CustomException;
import br.com.estefanosantos.model.FormaPagamento;

public interface FormaPagamentoService {

	void salvar(FormaPagamento formaPagamento) throws CustomException;
}	
