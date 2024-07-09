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

import br.com.estefanosantos.dto.ImagemProdutoDto;
import br.com.estefanosantos.exceptions.CustomException;
import br.com.estefanosantos.model.ImagemProduto;
import br.com.estefanosantos.service.ImagemProdutoService;

@RestController
public class ImagemProdutoController {
	
	@Autowired
	private ImagemProdutoService imagemProdutoService;
	
	@ResponseBody
	@PostMapping("/salvarImagemProduto")
	public ResponseEntity<ImagemProdutoDto> salvarImagemProduto(@RequestBody ImagemProduto imagemProduto) throws CustomException {
		
		if (imagemProduto == null) {
			throw new CustomException("Corpo da requisição nulo.");
		}
		
		if (imagemProduto.getId() != null) {
			throw new CustomException("Corpo da requisição não deve possuir id.");
		}
		
		if (imagemProduto.getImagemOriginal() == null) {
			throw new CustomException("É obrigatório enviar a imagem do produto.");
		}
		
		if (imagemProduto.getProduto().getId() == null) {
			throw new CustomException("Informe o id do produto.");
		}
		
		if (imagemProduto.getEmpresa().getId() == null) {
			throw new CustomException("Informe o id da empresa.");
		}
		
		ImagemProdutoDto dto = imagemProdutoService.salvarImagemProduto(imagemProduto);
		
		return new ResponseEntity<ImagemProdutoDto>(dto, HttpStatus.OK);
		
		
	}
	
	@ResponseBody
	@DeleteMapping("/apagarImagensPorProduto/{idProduto}")
	public ResponseEntity<String> apagarImagensPorProduto(@PathVariable("idProduto") Long idProduto) throws CustomException {
		
		if (idProduto == null) {
			throw new CustomException("Informe o id do produto.");
		}
		
		imagemProdutoService.apagarImagensPorProduto(idProduto);
		
		return new ResponseEntity<String>("Imagens do produto removidas.", HttpStatus.OK);
	}
	
	@ResponseBody
	@GetMapping("/obterImagensPorProduto/{idProduto}")
	public ResponseEntity<List<ImagemProdutoDto>> obterImagensPorProduto(@PathVariable("idProduto") Long idProduto) throws CustomException {
		
		if (idProduto == null) {
			throw new CustomException("Informe o id do produto");
		}
		
		List<ImagemProdutoDto> imagensProduto = imagemProdutoService.buscarImagensProduto(idProduto);
		
		return new ResponseEntity<List<ImagemProdutoDto>>(imagensProduto, HttpStatus.OK);
	}
	
	@ResponseBody
	@DeleteMapping("/apagarImagemProdutoPorId/{id}")
	public ResponseEntity<String> apagarImagemProdutoPorId(@PathVariable("id") Long id) throws CustomException {
		
		if (id == null) {
			throw new CustomException("Informe o id do produto");
		}
		
		imagemProdutoService.apagarImagemProdutoPorId(id);
		
		return new ResponseEntity<String>("Imagem removida.", HttpStatus.OK);
				
	}
	

}
