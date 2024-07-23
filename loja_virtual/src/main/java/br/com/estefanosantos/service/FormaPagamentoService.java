package br.com.estefanosantos.service;

import java.util.List;

import br.com.estefanosantos.dto.FormaPagamentoDto;
import br.com.estefanosantos.exceptions.CustomException;
import br.com.estefanosantos.model.FormaPagamento;

public interface FormaPagamentoService {

	void salvar(FormaPagamento formaPagamento) throws CustomException;
	
	List<FormaPagamentoDto> findByEmpresaId(Long idEmprsa) throws CustomException;
}	

