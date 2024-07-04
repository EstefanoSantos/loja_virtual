package br.com.estefanosantos.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import br.com.estefanosantos.exceptions.CustomException;
import br.com.estefanosantos.model.NotaFiscalCompra;
import br.com.estefanosantos.service.NotaFiscalCompraService;
import jakarta.validation.Valid;

@RestController
public class NotaFiscalCompraController {

	@Autowired
	NotaFiscalCompraService notaFiscalCompraService;
	
	@ResponseBody
	@PostMapping("/salvarNotaFiscal")
	public ResponseEntity<String> salvarNotaFiscal(@RequestBody @Valid NotaFiscalCompra notaFiscalCompra) throws CustomException {
		
		if (notaFiscalCompra == null) {
			throw new CustomException("Corpo da requisição vazio.");
		}
		
		if (notaFiscalCompra.getId() != null) {
			throw new CustomException("Corpo da requisição não deve possuir id");
		}
		
		if (notaFiscalCompra.getPessoa() == null || notaFiscalCompra.getPessoa().getId() == null 
				|| notaFiscalCompra.getPessoa().getId() <= 0) {
			throw new CustomException("Informe a Pessoa Jurídica que realizou a compra");
		}
		
		if (notaFiscalCompra.getEmpresa() == null || notaFiscalCompra.getEmpresa().getId() == null 
				|| notaFiscalCompra.getEmpresa().getId() <= 0) {
			throw new CustomException("Informe a empresa da qual a compra foi realizda.");
		}
		
		if (notaFiscalCompra.getContaPagar() == null || notaFiscalCompra.getContaPagar().getId() == null 
				|| notaFiscalCompra.getContaPagar().getId() <= 0) {
			throw new CustomException("Informe a conta pendente.");
		}
		
		notaFiscalCompraService.salvarNotaFiscal(notaFiscalCompra);
		
		return new ResponseEntity<String>("Nota Fiscal da Compra salva com sucesso!", HttpStatus.OK);
	}
	
	@ResponseBody
	@GetMapping("/buscarNotasDesc/{desc}")
	public ResponseEntity<List<NotaFiscalCompra>> buscarNotaPorDesc(@PathVariable("desc") String notaDesc) throws CustomException {
		
		if (notaDesc == null) {
			throw new CustomException("Informe a descrição da nota.");
		}
		
		List<NotaFiscalCompra> notas = notaFiscalCompraService.buscarPorDesc(notaDesc.toUpperCase().trim());
		
		return new ResponseEntity<List<NotaFiscalCompra>>(notas, HttpStatus.OK);
	}
	
	@ResponseBody
	@GetMapping("/buscarNotaPorSerie/{serie}")
	public ResponseEntity<NotaFiscalCompra> buscarNotaPorSerie(@PathVariable("serie") String serie) throws CustomException {
		
		if (serie == null) {
			throw new CustomException("Informe a serie da nota");
		}
		
		NotaFiscalCompra nota = notaFiscalCompraService.buscarPorSerieNota(serie);
		
		return new ResponseEntity<NotaFiscalCompra>(nota, HttpStatus.OK);
	}
	
	@ResponseBody
	@GetMapping("/buscarNotasPorEmpresa/{id}")
	public ResponseEntity<List<NotaFiscalCompra>> buscarNotasPorEmpresa(@PathVariable("id") Long id) throws CustomException {
		
		if (id == null) {
			throw new CustomException("Informe o id da empresa.");
		}
		
		List<NotaFiscalCompra> notas = notaFiscalCompraService.buscarPorEmpresa(id);
		
		return new ResponseEntity<List<NotaFiscalCompra>>(notas, HttpStatus.OK);
	}
	
	@ResponseBody
	@GetMapping("/buscarNotasPorPessoa/{id}")
	public ResponseEntity<List<NotaFiscalCompra>> buscarNotasPorPessoa(@PathVariable("id") Long id) throws CustomException {
		
		if (id == null) {
			throw new CustomException("Informe o id da pessoa");
		}
		
		List<NotaFiscalCompra> notas = notaFiscalCompraService.buscarPorPessoa(id);
		
		return new ResponseEntity<List<NotaFiscalCompra>>(notas, HttpStatus.OK);
	}
	
	@ResponseBody
	@DeleteMapping("/apagarNotaPorId/{id}")
	public ResponseEntity<String> apagarNotaPorId(@PathVariable("id") Long id) throws CustomException {
		
		if (id == null) {
			throw new CustomException("Informe o ide da nota fiscal.");
		}
		
		notaFiscalCompraService.apagarNotaFiscal(id);
		
		return new ResponseEntity<String>("Nota fiscal removida com sucesso!", HttpStatus.OK);
	}
}
