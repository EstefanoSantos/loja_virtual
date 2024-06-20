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
import br.com.estefanosantos.repository.PessoaJuridicaRepository;
import br.com.estefanosantos.service.PessoaJuridicaService;

@RestController
public class PessoaJuridicaController {
	
	@Autowired
	PessoaJuridicaService pessoaJuridicaService;
	
	@Autowired
	PessoaJuridicaRepository pessoaJuridicaRepository;
	
	@ResponseBody
	@PostMapping("/salvarPj")
	public ResponseEntity<PessoaJuridica> salvarPj(@RequestBody PessoaJuridica pessoaJuridica) throws CustomException {
		
		if (pessoaJuridica == null) {
			throw new CustomException("Pessoa Jurídica não pode ser NULL.");
		}
		
		PessoaJuridica pj = pessoaJuridicaRepository.existeCnpj(pessoaJuridica.getCnpj());
		
		if (pj != null) {
			throw new CustomException("Cnpj já cadastrado no sistema.");
		}
		
		pj = pessoaJuridicaRepository.existeEmail(pessoaJuridica.getEmail());
		
		if (pj != null) {
			throw new CustomException("Email para Pessoa Jurídica já cadastrado no sistema.");
		}
		
		pj = pessoaJuridicaRepository.existeInscricaoEstadual(pessoaJuridica.getInscricaoEstadual());
		
		if (pj != null) {
			throw new CustomException("Já existe Pessoa Jurídica com inscrição estadual de número " + pessoaJuridica.getInscricaoEstadual());
		}
		
		pj = pessoaJuridicaService.salvarPessoaJuridica(pessoaJuridica);
		
		return new ResponseEntity<>(pj, HttpStatus.OK);
	}

}
