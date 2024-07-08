package br.com.estefanosantos.service;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.List;

import br.com.estefanosantos.exceptions.CustomException;
import br.com.estefanosantos.model.Produto;
import jakarta.mail.MessagingException;

public interface ProdutoService {

	void salvarProduto(Produto produto) throws CustomException, IOException;
	
	void deleteProduto(Long id) throws CustomException;
	
	List<Produto> encontarPorNome(String nome) throws CustomException;
	
	void verificarEstoqueProdutos() throws UnsupportedEncodingException, MessagingException;
}
