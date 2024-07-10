package br.com.estefanosantos.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import br.com.estefanosantos.exceptions.CustomException;
import br.com.estefanosantos.model.FormaPagamento;
import br.com.estefanosantos.service.FormaPagamentoService;

@RestController
public class FormaPagamentoController {
	
	@Autowired
	private FormaPagamentoService formaPagamentoService;
	
	@ResponseBody
	@PostMapping("/salvarFormaPagamento")
	public ResponseEntity<String> salvarFormaPagamento(@RequestBody FormaPagamento formaPagamento) throws CustomException {
		
		if (formaPagamento == null) {
			throw new CustomException("Corpo da requisição vazio.");
		}
		
		if (formaPagamento.getId() != null ) {
			throw new CustomException("Corpo da requisição não pode possuir id.");
		}
		
		if (formaPagamento.getEmpresa() == null || (formaPagamento.getEmpresa().getId()== null || formaPagamento.getEmpresa().getId() <= 0)) {
			throw new CustomException("Informe a empresa.");
		}
		
		formaPagamentoService.salvar(formaPagamento);
		
		return new ResponseEntity<String>("Forma de pagamento cadastrada: " +formaPagamento.getFormaPagamento(), HttpStatus.OK);
	}
}
