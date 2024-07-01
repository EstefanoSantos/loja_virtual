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
import br.com.estefanosantos.model.Produto;
import br.com.estefanosantos.service.ProdutoService;
import jakarta.validation.Valid;

@RestController
public class ProdutoController {

	@Autowired
	ProdutoService produtoService;
	
	@ResponseBody
	@PostMapping("/salvarProduto")
	public ResponseEntity<String> salvarProduto(@RequestBody @Valid Produto produto) throws CustomException {
		
		if (produto == null) {
			throw new CustomException("Corpo da requisição vazio.");
		}
		
		if (produto.getId() != null) {
			throw new CustomException("Corpo da requisição não pode possuir id.");
		}
		
		if (produto.getEmpresa() == null || produto.getEmpresa().getId() <= 0) {
			throw new CustomException("Informe o fornecedor do produto.");
		}
		
		if (produto.getCategoriaProduto() == null || produto.getCategoriaProduto().getId() <= 0) {
			throw new CustomException("Informe a categoria do produto.");
		}
		
		if (produto.getMarcaProduto() == null || produto.getMarcaProduto().getId() <= 0) {
			throw new CustomException("Informe a marca do produto");
		}
		
		produtoService.salvarProduto(produto);
		
		return new ResponseEntity<String>("Produto salvo.", HttpStatus.OK);
	}
	
	@ResponseBody
	@DeleteMapping("/deleteProduto/{id}")
	public ResponseEntity<String> deleteProduto(@PathVariable("id") Long id) throws CustomException {
		
		if (id == null) {
			throw new CustomException("Id não pode ser nulo");
		}
		
		produtoService.deleteProduto(id);
		
		return new ResponseEntity<String>("Produto removido com sucesso!", HttpStatus.OK);
	}
	
	@ResponseBody
	@GetMapping("/encontrarProdutosNome")
	public ResponseEntity<List<Produto>> encontrarPorNome(@PathVariable("nome") String nome) throws CustomException {
		
		if (nome == null) {
			throw new CustomException("Nome está nulo");
		}
		
		List<Produto> produtosEncontrados = produtoService.encontarPorNome(nome);
		
		return new ResponseEntity<List<Produto>>(produtosEncontrados, HttpStatus.OK);
	}
}
