package br.com.estefanosantos.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import br.com.estefanosantos.exceptions.CustomException;
import br.com.estefanosantos.model.PessoaFisica;
import br.com.estefanosantos.service.PessoaFisicaService;
import br.com.estefanosantos.util.ValidaCpf;

@RestController
public class PessoaFisicaController {
	
	@Autowired
	PessoaFisicaService pessoaFisicaService;
	
	@ResponseBody
	@PostMapping("/salvarPf")
	public ResponseEntity<PessoaFisica> salvarPf(@RequestBody PessoaFisica pessoaFisica) throws CustomException {
		
		if (pessoaFisica.getId() != null) {
			throw new CustomException("Corpo da requisição não pode possuir id.");
		}
		
		if (!ValidaCpf.isCPF(pessoaFisica.getCpf())) {
			throw new CustomException("CPF inválido");
		}
		
		PessoaFisica pf = pessoaFisicaService.salvarPessoaFisica(pessoaFisica);
		
		return new ResponseEntity<>(pf, HttpStatus.OK);
	}
	

}
