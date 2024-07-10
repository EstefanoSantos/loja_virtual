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

import br.com.estefanosantos.dto.AvaliacaoProdutoDto;
import br.com.estefanosantos.exceptions.CustomException;
import br.com.estefanosantos.model.AvaliacaoProduto;
import br.com.estefanosantos.service.AvaliacaoProdutoService;
import jakarta.validation.Valid;

@RestController
public class AvalicaoProdutoController {

	@Autowired
	private AvaliacaoProdutoService avaliacaoProdutoService;

	@ResponseBody
	@PostMapping("/salvarAvaliacaoProduto")
	public ResponseEntity<String> salvarAvaliacaoProduto(@RequestBody @Valid AvaliacaoProduto avaliacaoProduto)
			throws CustomException {

		if (avaliacaoProduto == null) {
			throw new CustomException("Corpo da requisição vazio.");
		}

		if (avaliacaoProduto.getId() != null) {
			throw new CustomException("Corpo da requisição não deve possuir id.");
		}

		if (avaliacaoProduto.getPessoa() == null
				|| (avaliacaoProduto.getPessoa().getId() == null || avaliacaoProduto.getPessoa().getId() <= 0)) {
			throw new CustomException("Informe o id da pessoa.");
		}

		if (avaliacaoProduto.getProduto() == null
				|| (avaliacaoProduto.getProduto().getId() == null || avaliacaoProduto.getProduto().getId() <= 0)) {
			throw new CustomException("Informe o id do produto.");
		}

		if (avaliacaoProduto.getEmpresa() == null
				|| (avaliacaoProduto.getEmpresa().getId() == null || avaliacaoProduto.getEmpresa().getId() <= 0)) {
			throw new CustomException("Informe o id da empresa.");
		}

		avaliacaoProdutoService.salvarAvaliacaoProduto(avaliacaoProduto);

		return new ResponseEntity<String>("Avaliação Salva com sucesso!", HttpStatus.OK);
	}

	@ResponseBody
	@GetMapping("/buscarAvaliacaoPorProduto/{idProduto}")
	public ResponseEntity<List<AvaliacaoProdutoDto>> buscarAvaliacaoPorProduto(
			@PathVariable("idProduto") Long idProduto) throws CustomException {

		if (idProduto == null) {
			throw new CustomException("Informe o id do produto.");
		}

		List<AvaliacaoProdutoDto> avaliacoes = avaliacaoProdutoService.buscarAvaliacaoPorProduto(idProduto);

		return new ResponseEntity<List<AvaliacaoProdutoDto>>(avaliacoes, HttpStatus.OK);
	}

	@ResponseBody
	@GetMapping("/buscarAvaliacaoPorProdutoPessoa/{idProduto}/{idPessoa}")
	public ResponseEntity<List<AvaliacaoProdutoDto>> buscarAvaliacaoPorProdutoPessoa(
			@PathVariable("idProduto") Long idProduto, @PathVariable("idPessoa") Long idPessoa) throws CustomException {

		if (idProduto == null) {
			throw new CustomException("Informe o id do produto.");
		}

		if (idPessoa == null) {
			throw new CustomException("Informe o id da pessoa.");
		}

		List<AvaliacaoProdutoDto> avaliacoes = avaliacaoProdutoService.buscarAvaliacaoPorProdutoPessoa(idProduto,
				idPessoa);

		return new ResponseEntity<List<AvaliacaoProdutoDto>>(avaliacoes, HttpStatus.OK);

	}

	@ResponseBody
	@GetMapping("/buscarAvaliacaoPorPessoa/{idPessoa}")
	public ResponseEntity<List<AvaliacaoProdutoDto>> buscarAvaliacaoPorPessoa(@PathVariable("idPessoa") Long idPessoa)
			throws CustomException {

		if (idPessoa == null) {
			throw new CustomException("Informe o id da pessoa.");
		}

		List<AvaliacaoProdutoDto> avaliacoes = avaliacaoProdutoService.buscarAvaliacaoPorPessoa(idPessoa);

		return new ResponseEntity<List<AvaliacaoProdutoDto>>(avaliacoes, HttpStatus.OK);
	}
	
	@ResponseBody
	@DeleteMapping("/apagarAvaliacaoPessoa/{idAvaliacao}/{idPessoa}")
	public ResponseEntity<String> apagarAvaliacaoPessoa(@PathVariable("idAvaliacao") Long idAvaliacao,
			@PathVariable("idPessoa") Long idPessoa) throws CustomException {
		
		if (idAvaliacao == null) {
			throw new CustomException("Informe o id da avaliação.");
		}
		
		if (idPessoa == null) {
			throw new CustomException("Informe o id da pessoa.");
		}
		
		avaliacaoProdutoService.apagarAvaliacaoPessoa(idAvaliacao, idPessoa);
		
		return new ResponseEntity<String>("Avaliação removida com sucesso!", HttpStatus.OK);
		
	}

}
