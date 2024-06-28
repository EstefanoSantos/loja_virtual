package br.com.estefanosantos.service;

import java.util.List;

import br.com.estefanosantos.dto.CategoriaProdutoDto;
import br.com.estefanosantos.exceptions.CustomException;
import br.com.estefanosantos.model.CategoriaProduto;

public interface CategoriaProdutoService {
	
	CategoriaProdutoDto salvarCategoria(CategoriaProduto produto) throws CustomException;
	
	CategoriaProdutoDto atualizarCategoria(CategoriaProduto produto) throws CustomException;
	
	void deletarCategoria(Long id) throws CustomException;
	
	CategoriaProdutoDto selecionarCategoria(Long id) throws CustomException;
	
	List<CategoriaProdutoDto> selecionarTodos();

}
