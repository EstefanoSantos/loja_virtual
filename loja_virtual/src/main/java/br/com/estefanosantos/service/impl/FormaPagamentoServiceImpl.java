package br.com.estefanosantos.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.estefanosantos.exceptions.CustomException;
import br.com.estefanosantos.model.FormaPagamento;
import br.com.estefanosantos.repository.FormaPagamentoRepository;
import br.com.estefanosantos.repository.PessoaJuridicaRepository;
import br.com.estefanosantos.service.FormaPagamentoService;

@Service
public class FormaPagamentoServiceImpl implements FormaPagamentoService {
	
	@Autowired
	private FormaPagamentoRepository formaPagamentoRepository;
	
	@Autowired
	private PessoaJuridicaRepository pessoaJuridicaRepository;

	@Override
	public void salvar(FormaPagamento formaPagamento) throws CustomException {
		
		if (!pessoaJuridicaRepository.existsById(formaPagamento.getEmpresa().getId())) {
			throw new CustomException("Não foi encontrado empresa com id fornecido: " + formaPagamento.getEmpresa().getId());
		}
		
		formaPagamentoRepository.save(formaPagamento);
		
	}

}
