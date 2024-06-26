package br.com.estefanosantos.controller;

import java.util.List;

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
		
		if (!ValidaCnpj.isCNPJ(pessoaJuridica.getCnpj())) {
			throw new CustomException("CNPJ inválido");
		}	
		
		PessoaJuridica pj = pessoaJuridicaService.salvarPessoaJuridica(pessoaJuridica);
		
		return new ResponseEntity<>(pj, HttpStatus.OK);
	}
	
	@ResponseBody
	@GetMapping("/pessoasPj/{nome}")
	public ResponseEntity<List<PessoaJuridica>> buscarPorNome(@PathVariable("nome") String nome) throws CustomException {
		if (nome == null) {
			throw new CustomException("Path variable vazia.");
		}
		
		List<PessoaJuridica> pessoas = pessoaJuridicaService.buscarPorNome(nome);
		
		return new ResponseEntity<>(pessoas, HttpStatus.OK);
	}
	
	@ResponseBody
	@GetMapping("/buscarPorCnpj/{cnpj}") 
	public ResponseEntity<PessoaJuridica> buscarPorCnpj(@PathVariable("cnpj") String cnpj) throws CustomException {
		if (cnpj == null) {
			throw new CustomException("Path variable vazia.");
		}
		PessoaJuridica pj = pessoaJuridicaService.buscarPorCnpj(cnpj);
		
		return new ResponseEntity<>(pj, HttpStatus.OK);
	
	}
	
	@ResponseBody
	@GetMapping("/buscarPorIE/{IE}")
	public ResponseEntity<PessoaJuridica> buscarPorIE(@PathVariable("IE") String inscricao) throws CustomException {
		if (inscricao == null) {
			throw new CustomException("Path variable vazia.");
		}
		PessoaJuridica pj = pessoaJuridicaService.buscarPorInscricaoEstadual(inscricao);
		
		return new ResponseEntity<>(pj, HttpStatus.OK);
	}

}
