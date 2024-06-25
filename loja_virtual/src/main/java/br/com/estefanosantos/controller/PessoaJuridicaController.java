package br.com.estefanosantos.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import br.com.estefanosantos.dto.CepDto;
import br.com.estefanosantos.exceptions.CustomException;
import br.com.estefanosantos.model.PessoaJuridica;
import br.com.estefanosantos.service.ConsumoApis;
import br.com.estefanosantos.service.PessoaJuridicaService;
import br.com.estefanosantos.util.ValidaCnpj;
import jakarta.validation.Valid;

@RestController
public class PessoaJuridicaController {
	
	@Autowired
	private PessoaJuridicaService pessoaJuridicaService;
	
	@Autowired
	private ConsumoApis api;
	
	@ResponseBody
	@GetMapping("/consultaCep/{cep}")
	public ResponseEntity<CepDto> consultaCep(@PathVariable("cep") String cep) {
		
		CepDto dto = api.consultaCep(cep);
		
		return new ResponseEntity<>(dto, HttpStatus.OK);
	}
	
	@ResponseBody
	@PostMapping("/salvarPj")
	public ResponseEntity<PessoaJuridica> salvarPj(@RequestBody @Valid PessoaJuridica pessoaJuridica) throws CustomException {
		
		if (pessoaJuridica == null) {
			throw new CustomException("Pessoa Jurídica não pode ser NULL.");
		}
		
		if (!ValidaCnpj.isCNPJ(pessoaJuridica.getCnpj())) {
			throw new CustomException("CNPJ inválido");
		}		
		
		PessoaJuridica pj = pessoaJuridicaService.salvarPessoaJuridica(pessoaJuridica);
		
		return new ResponseEntity<>(pj, HttpStatus.OK);
	}

}
