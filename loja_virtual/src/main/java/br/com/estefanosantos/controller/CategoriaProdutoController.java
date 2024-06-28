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

import br.com.estefanosantos.dto.CategoriaProdutoDto;
import br.com.estefanosantos.exceptions.CustomException;
import br.com.estefanosantos.model.CategoriaProduto;
import br.com.estefanosantos.service.CategoriaProdutoService;

@RestController
public class CategoriaProdutoController {
	
	@Autowired
	private CategoriaProdutoService categoriaProdutoService;
	
	@ResponseBody
	@PostMapping("/salvarCategoria")
	public ResponseEntity<CategoriaProdutoDto> salvarCategoria(@RequestBody CategoriaProduto produto) throws CustomException {
		
		if (produto == null ) {
			throw new CustomException("Corpo da requisição vazio.");			
		}
		
		if (produto.getId() != null) {
			throw new CustomException("Requisição não deve possuir campo id.");
		}
		
		if (produto.getEmpresa() == null|| produto.getEmpresa().getId() == null || produto.getEmpresa().getId() <= 0) {
			throw new CustomException("Deve ser informado o id da empresa a cadastrar a categoria.");
		}
		
		CategoriaProdutoDto produtoSalvo = categoriaProdutoService.salvarCategoria(produto);
		
		return new ResponseEntity<CategoriaProdutoDto>(produtoSalvo, HttpStatus.OK);
	}
	
	@ResponseBody
	@PostMapping("/atualizarCategoria")
	public ResponseEntity<CategoriaProdutoDto> atualizarCategoria(@RequestBody CategoriaProduto produto) throws CustomException {
		
		if (produto == null) {
			throw new CustomException("Corpo da requisição vazio.");			
		}
		
		if (produto.getId() == null) {
			throw new CustomException("É necessário o id do produto para atualizá-lo.");
		}
		
		CategoriaProdutoDto dto = categoriaProdutoService.atualizarCategoria(produto);
		
		return new ResponseEntity<CategoriaProdutoDto>(dto, HttpStatus.OK);
	}
	
	@ResponseBody
	@DeleteMapping("/apagarCategoria/{id}")
	public ResponseEntity<String> apagarCategoria(@PathVariable("id") Long id) throws CustomException {
		
		if (id == null) {
			throw new CustomException("É necessário o id da categoria pra removê-la.");
		}
		
		categoriaProdutoService.deletarCategoria(id);
		
		return new ResponseEntity<String>("Categoria removida!", HttpStatus.OK);
	}
	
	@ResponseBody
	@GetMapping("/buscarCategoria/{id}")
	public ResponseEntity<CategoriaProdutoDto> buscarCategoria(@PathVariable("id") Long id) throws CustomException {
		
		if (id == null) {
			throw new CustomException("Informe o id da categoria.");
		}
		
		CategoriaProdutoDto dto = categoriaProdutoService.selecionarCategoria(id);
		
		return new ResponseEntity<CategoriaProdutoDto>(dto, HttpStatus.OK);
	}
	
	@ResponseBody
	@GetMapping("/buscarCategorias")
	public ResponseEntity<List<CategoriaProdutoDto>> listaCategorias() {
		return new ResponseEntity<List<CategoriaProdutoDto>>(categoriaProdutoService.selecionarTodos(), HttpStatus.OK);
	}
}
