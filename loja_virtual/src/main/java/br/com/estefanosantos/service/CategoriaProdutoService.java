package br.com.estefanosantos.service;

import br.com.estefanosantos.dto.CategoriaProdutoDto;
import br.com.estefanosantos.exceptions.CustomException;
import br.com.estefanosantos.model.CategoriaProduto;

public interface CategoriaProdutoService {
	
	CategoriaProdutoDto salvarProduto(CategoriaProduto produto) throws CustomException;

}
