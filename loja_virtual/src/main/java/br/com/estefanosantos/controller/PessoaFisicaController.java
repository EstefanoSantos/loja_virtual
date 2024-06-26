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

import br.com.estefanosantos.dto.PessoaFisicaRequestDto;
import br.com.estefanosantos.exceptions.CustomException;
import br.com.estefanosantos.model.PessoaFisica;
import br.com.estefanosantos.service.PessoaFisicaService;
import br.com.estefanosantos.service.impl.ContagemAcessoApiService;
import br.com.estefanosantos.util.ValidaCpf;
import jakarta.validation.Valid;

@RestController
public class PessoaFisicaController {
	
	@Autowired
	private PessoaFisicaService pessoaFisicaService;
	
	@Autowired
	private ContagemAcessoApiService contagemApiService;
	
	@ResponseBody
	@PostMapping("/salvarPf")
	public ResponseEntity<PessoaFisica> salvarPf(@RequestBody @Valid PessoaFisicaRequestDto pessoaFisica) throws CustomException {
		
		PessoaFisica pf = pessoaFisica.getPessoaFisica();
		String cnpj = pessoaFisica.getCnpj();
		
		if (pf.getId() != null) {
			throw new CustomException("Corpo da requisição não pode possuir id.");
		}
		
		if (!ValidaCpf.isCPF(pf.getCpf())) {
			throw new CustomException("CPF inválido");
		}
		
		if (cnpj == null) {
			throw new CustomException("Cnpj ausente na requisição.");
		}
		
		pf = pessoaFisicaService.salvarPessoaFisica(pf, cnpj);
		
		return new ResponseEntity<>(pf, HttpStatus.OK);
	}
	
	@GetMapping("/buscarPfNome/{nome}")
	public ResponseEntity<List<PessoaFisica>> buscarPfPorNome(@PathVariable("nome") String nome) throws CustomException {
		
		if (nome == null) {
			throw new CustomException("Variável de caminho ausente.");
		}
		
		List<PessoaFisica> pessoas = pessoaFisicaService.buscarPessoasPorNome(nome);
		
		contagemApiService.atualizarContagemBuscaPfNome();
		
		return new ResponseEntity<>(pessoas, HttpStatus.OK);
	}
	
	@ResponseBody
	@GetMapping("/buscarPorCpf/{cpf}")
	public ResponseEntity<PessoaFisica> buscarPorCpf(@PathVariable("cpf") String cpf) throws CustomException {
		if (cpf == null) {
			throw new CustomException("Variável de caminho ausente.");
		}
		
		PessoaFisica pf = pessoaFisicaService.buscarPorCpf(cpf);
		
		contagemApiService.atualizarContagemBuscaPfCpf();
		
		return new ResponseEntity<>(pf, HttpStatus.OK);
	}

}
