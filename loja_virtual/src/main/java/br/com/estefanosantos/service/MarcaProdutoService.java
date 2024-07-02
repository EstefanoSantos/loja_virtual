package br.com.estefanosantos.service;

import java.util.List;

import br.com.estefanosantos.exceptions.CustomException;
import br.com.estefanosantos.model.MarcaProduto;

public interface MarcaProdutoService {

	public void salvarMarcaProduto(MarcaProduto marcaProduto) throws CustomException;
	
	public MarcaProduto buscarMarcaProduto(Long id) throws CustomException;
	
	public void apagarMarcaProduto(Long id) throws CustomException;
	
	public List<MarcaProduto> buscarProdutos() throws CustomException;
	
	public List<MarcaProduto> buscarMarcaNome(String nome) throws CustomException;
}
