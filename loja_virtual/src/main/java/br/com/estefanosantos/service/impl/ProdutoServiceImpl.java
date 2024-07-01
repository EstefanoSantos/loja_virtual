package br.com.estefanosantos.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.estefanosantos.exceptions.CustomException;
import br.com.estefanosantos.model.Produto;
import br.com.estefanosantos.repository.ProdutoRepository;
import br.com.estefanosantos.service.ProdutoService;

@Service
public class ProdutoServiceImpl implements ProdutoService {
 
	@Autowired
	ProdutoRepository produtoRepository;

	@Override
	public void salvarProduto(Produto produto) throws CustomException {
		
		List<Produto> produtos = produtoRepository.buscarProdutoNome(produto.getNome().toUpperCase(), produto.getEmpresa().getId());
		
		if (!produtos.isEmpty()) {
			throw new CustomException("Produto já cadastrado.");
		}
		
		produtoRepository.save(produto);
	}
	
	@Override
	public void deleteProduto(Long id) throws CustomException {
		
		if (produtoRepository.checkById(id) == false) {
			throw new CustomException("Não existe produto cadastrado com esse id.");
		}
		
		produtoRepository.deleteById(id);
	}

	@Override
	public List<Produto> encontarPorNome(String nome) throws CustomException {
		
		List<Produto> produtos = produtoRepository.buscarProdutoNome(nome.toUpperCase());
		
		if (produtos == null) {
			throw new CustomException("Não foi encontrado produtos com esse nome");
		}
		
		return produtos;
		
	}
}
