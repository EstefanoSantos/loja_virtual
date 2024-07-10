package br.com.estefanosantos.service;

import java.util.List;

import br.com.estefanosantos.dto.AvaliacaoProdutoDto;
import br.com.estefanosantos.exceptions.CustomException;
import br.com.estefanosantos.model.AvaliacaoProduto;

public interface AvaliacaoProdutoService {
	
	public void salvarAvaliacaoProduto(AvaliacaoProduto avaliacaoProduto) throws CustomException;
	
	public List<AvaliacaoProdutoDto> buscarAvaliacaoPorProduto(Long idProduto) throws CustomException;

	public List<AvaliacaoProdutoDto> buscarAvaliacaoPorProdutoPessoa(Long idProduto, Long idPessoa) throws CustomException;
	
	public List<AvaliacaoProdutoDto> buscarAvaliacaoPorPessoa(Long idPessoa) throws CustomException;
	
	public void apagarAvaliacaoPessoa(Long idAvaliacao, Long idPessoa) throws CustomException;

}
