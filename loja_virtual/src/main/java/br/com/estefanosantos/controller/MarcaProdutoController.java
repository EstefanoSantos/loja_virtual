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
import br.com.estefanosantos.model.MarcaProduto;
import br.com.estefanosantos.service.MarcaProdutoService;
import jakarta.validation.Valid;

@RestController
public class MarcaProdutoController {
	
	@Autowired
	MarcaProdutoService marcaProdutoService;
	
	@ResponseBody
	@PostMapping("/salvarMarcaProduto")
	public ResponseEntity<String> salvarMarcaProduto(@RequestBody @Valid MarcaProduto marcaProduto) throws CustomException {
		
		if (marcaProduto == null) {
			throw new CustomException("Corpo da requisição vazio.");
		}
		
		if (marcaProduto.getId() != null) {
			throw new CustomException("Corpo da requisição não deve possuir id.");
		}
		
		if (marcaProduto.getEmpresa().getId() == null) {
			throw new CustomException("Informe o id da empresa fornecedora.");
		}
		
		marcaProdutoService.salvarMarcaProduto(marcaProduto);
		
		return new ResponseEntity<String>("Marca do produto salva com sucesso!", HttpStatus.OK);
	}
	
	@ResponseBody
	@GetMapping("/buscarMarcaProduto/{id}")
	public ResponseEntity<MarcaProduto> buscarMarcaProdut(@PathVariable("id") Long id) throws CustomException {
		
		if (id == null) {
			throw new CustomException("Informe o id da marca.");
		}
		
		MarcaProduto marcaProduto = marcaProdutoService.buscarMarcaProduto(id);
		
		return new ResponseEntity<MarcaProduto>(marcaProduto, HttpStatus.OK);
	}
	
	@ResponseBody
	@DeleteMapping("/deleteMarcaProduto/{id}")
	public ResponseEntity<String> deleteMarcaProduto(@PathVariable("id") Long id) throws CustomException {
		
		if (id == null) {
			throw new CustomException("Informe o id da marca que desejar remover.");
		}
		
		marcaProdutoService.apagarMarcaProduto(id);
		
		return new ResponseEntity<String>("Marca removida com sucesso!", HttpStatus.OK);
	}
	
	@ResponseBody
	@GetMapping("/buscarMarcas")
	public ResponseEntity<List<MarcaProduto>> buscarMarcas() throws CustomException {
		
		List<MarcaProduto> marcas = marcaProdutoService.buscarProdutos();
		
		return new ResponseEntity<List<MarcaProduto>>(marcas, HttpStatus.OK);
	}
	
	@ResponseBody
	@GetMapping("/buscarMarcaProdutoNome/{nome}")
	public ResponseEntity<List<MarcaProduto>> buscarMarcasNome(@PathVariable("nome") String nome) throws CustomException {
		
		if (nome == null) {
			throw new CustomException("Informe o nome do produto");
		}
		
		List<MarcaProduto> marcas = marcaProdutoService.buscarMarcaNome(nome.toUpperCase());
		
		return new ResponseEntity<List<MarcaProduto>>(marcas, HttpStatus.OK);
	}

}
