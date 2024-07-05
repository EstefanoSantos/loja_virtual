package br.com.estefanosantos.service;

import br.com.estefanosantos.exceptions.CustomException;
import br.com.estefanosantos.model.NotaItemProduto;

public interface NotaItemProdutoService {
	
	NotaItemProduto buscarPorProdutoNota(Long idProduto, Long idNota) throws CustomException;
	
	void salvarNotaItemProduto(NotaItemProduto notaItemProduto) throws CustomException;
	
	void apagarNotaItemProduto(Long id) throws CustomException;

}
