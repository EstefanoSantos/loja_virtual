package br.com.estefanosantos.controller;

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
import br.com.estefanosantos.model.NotaItemProduto;
import br.com.estefanosantos.service.NotaItemProdutoService;
import jakarta.validation.Valid;

@RestController
public class NotaItemProdutoController {
	
	@Autowired
	private NotaItemProdutoService notaItemProdutoService;
	
	@ResponseBody
	@PostMapping("/salvarNotaItemProduto")
	public ResponseEntity<String> salvarNotaItemProduto(@RequestBody @Valid NotaItemProduto notaItemProduto) throws CustomException {
		
		if (notaItemProduto == null) {
			throw new CustomException("Corpo da requisição vazio.");
		}
		
		if (notaItemProduto.getId() != null) {
			throw new CustomException("Requisição não pode possuir id.");
		}
		
		if (notaItemProduto.getEmpresa() == null ||
				notaItemProduto.getEmpresa().getId() == null|| notaItemProduto.getEmpresa().getId() <= 0) {
			throw new CustomException("Informe a empresa.");
		}
		
		if (notaItemProduto.getNotaFiscalCompra() == null ||
				notaItemProduto.getNotaFiscalCompra().getId() == null|| notaItemProduto.getNotaFiscalCompra().getId() <= 0) {
			throw new CustomException("Informe a nota fiscal.");
		}
		
		if (notaItemProduto.getProduto() == null ||
				notaItemProduto.getProduto().getId() == null|| notaItemProduto.getProduto().getId() <= 0) {
			throw new CustomException("Informe o produto.");
		}
		
		if (notaItemProduto.getQuantidade() <= 0) {
			throw new CustomException("Informe a quantidade comprada do produto.");
		}
		
		notaItemProdutoService.salvarNotaItemProduto(notaItemProduto);
		
		return new ResponseEntity<String>("Nota de venda do produto salva com sucesso!", HttpStatus.OK);
	}
	
	@ResponseBody
	@GetMapping("/buscarItemPorProdutoNota/{idProduto}/{idNota}")
	public ResponseEntity<NotaItemProduto> buscarItemPorProdutoNota(
			@PathVariable("idProduto")Long idProduto, @PathVariable("idNota")Long idNota) throws CustomException {
		
		if (idProduto == null || idNota == null) {
			throw new CustomException("Informe o id do produto e da nota.");
		}
		NotaItemProduto nota = notaItemProdutoService.buscarPorProdutoNota(idProduto, idNota);
		
		return new ResponseEntity<NotaItemProduto>(nota, HttpStatus.OK);
		 
	}
	
	@ResponseBody
	@DeleteMapping("/apagarItemPorId/{id}")
	public ResponseEntity<String> apagarItemPorId(@PathVariable("id") Long id) throws CustomException {
		
		if (id == null) {
			throw new CustomException("Informe o id da nota.");
		}
		
		notaItemProdutoService.apagarNotaItemProduto(id);
		
		return new ResponseEntity<String>("Nota removida com sucesso!", HttpStatus.OK);
	}

}
