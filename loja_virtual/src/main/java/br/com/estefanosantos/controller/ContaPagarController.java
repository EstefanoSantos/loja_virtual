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

import br.com.estefanosantos.exceptions.CustomException;
import br.com.estefanosantos.model.ContaPagar;
import br.com.estefanosantos.service.ContaPagarService;
import jakarta.validation.Valid;

@RestController
public class ContaPagarController {
	
	@Autowired
	private ContaPagarService contaPagarService;
	
	@ResponseBody
	@PostMapping("/salvarContaPagar")
	public ResponseEntity<String> salvarContaPagar(@RequestBody @Valid ContaPagar contaPagar) throws CustomException {
		
		if (contaPagar == null) {
			throw new CustomException("Informe uma conta a ser paga no corpo da requisição.");
		}
		
		if (contaPagar.getId() != null) {
			throw new CustomException("O corpo da requisição não deve possuir id.");
		}
		
		contaPagarService.salvarContaPagar(contaPagar);
		
		return new ResponseEntity<String>("Conta a pagar salva com sucesso!", HttpStatus.OK);
		
	}
	
	@ResponseBody
	@GetMapping("/deletarContaPagar/{id}")
	public ResponseEntity<String> deletarContaPagar(@PathVariable("id") Long id) throws CustomException {
		
		if (id == null) {
			throw new CustomException("Informe o id da conta a ser apagada.");
		}
		
		contaPagarService.apagarContaPagar(id);
		
		return new ResponseEntity<String>("Conta a pagar removida!", HttpStatus.OK);
	}
	
	@ResponseBody
	@GetMapping("/buscarContaPagarId/{id}")
	public ResponseEntity<ContaPagar> buscarContaPagarId(@PathVariable("id") Long id) throws CustomException {
		
		if (id == null) {
			throw new CustomException("Informe o id da conta que desejas buscar.");
		}
		
		ContaPagar conta = contaPagarService.selecionarContaPagar(id);
		
		return new ResponseEntity<ContaPagar>(conta, HttpStatus.OK);
	}
	
	@ResponseBody
	@GetMapping("/buscarContasPagar")
	public ResponseEntity<List<ContaPagar>> buscarContasPagar() throws CustomException {
		
		List<ContaPagar> contas = contaPagarService.selecionarContasPagar();
		
		return new ResponseEntity<List<ContaPagar>>(contas, HttpStatus.OK);
	}
	
	@ResponseBody
	@GetMapping("/buscarContasPagarDesc/{desc}")
	public ResponseEntity<List<ContaPagar>> buscarContasPagarDesc(@PathVariable("desc") String descricao) throws CustomException {
		
		if (descricao == null) {
			throw new CustomException("Informe a descrição das contas.");
		}
		
		List<ContaPagar> contas = contaPagarService.selecionarContaPagarDesc(descricao.toUpperCase().trim());
		
		return new ResponseEntity<List<ContaPagar>>(contas, HttpStatus.OK);
	}
	
}	
