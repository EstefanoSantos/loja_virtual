package br.com.estefanosantos.service;

import java.util.List;

import br.com.estefanosantos.exceptions.CustomException;
import br.com.estefanosantos.model.Produto;

public interface ProdutoService {

	void salvarProduto(Produto produto) throws CustomException;
	
	void deleteProduto(Long id) throws CustomException;
	
	List<Produto> encontarPorNome(String nome) throws CustomException;
}
