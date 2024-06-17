package br.com.estefanosantos.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import br.com.estefanosantos.exceptions.CustomException;
import br.com.estefanosantos.model.PessoaJuridica;
import br.com.estefanosantos.service.PessoaJuridicaService;

@RestController
public class PessoaJuridicaController {
	
	@Autowired
	PessoaJuridicaService pessoaJuridicaService;
	
	@ResponseBody
	@PostMapping("/salvarPj")
	public ResponseEntity<PessoaJuridica> salvarPj(@RequestBody PessoaJuridica pessoaJuridica) throws CustomException {
		
		if (pessoaJuridica == null) {
			throw new CustomException("Pessoa Jurídica não pode ser NULL.");
		}
		
		PessoaJuridica pj = pessoaJuridicaService.salvarPessoaJuridica(pessoaJuridica);
		
		return new ResponseEntity<>(pj, HttpStatus.OK);
	}

}
