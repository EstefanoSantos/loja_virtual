package br.com.estefanosantos.service;

import java.util.List;

import br.com.estefanosantos.dto.ImagemProdutoDto;
import br.com.estefanosantos.exceptions.CustomException;
import br.com.estefanosantos.model.ImagemProduto;

public interface ImagemProdutoService {
	
	public List<ImagemProdutoDto> buscarImagensProduto(Long idProduto) throws CustomException;
	
	public void apagarImagensPorProduto(Long idProduto) throws CustomException;
	
	public void apagarImagemProdutoPorId(Long id) throws CustomException;
	
	public ImagemProdutoDto salvarImagemProduto(ImagemProduto imagemProduto) throws CustomException;
}
