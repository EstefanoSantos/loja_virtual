package br.com.estefanosantos.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.estefanosantos.dto.CategoriaProdutoDto;
import br.com.estefanosantos.exceptions.CustomException;
import br.com.estefanosantos.model.CategoriaProduto;
import br.com.estefanosantos.repository.CategoriaProdutoRepository;
import br.com.estefanosantos.service.CategoriaProdutoService;

@Service
public class CategoriaProdutoServiceImpl implements CategoriaProdutoService{
	
	@Autowired
	private CategoriaProdutoRepository categoriaProdutoRepository;

	@Override
	public CategoriaProdutoDto salvarProduto(CategoriaProduto produto) throws CustomException {
		
		CategoriaProduto pd = categoriaProdutoRepository.buscarPorCategoria(produto.getNomeCategoria());
		
		if (pd == null) {
			pd  = categoriaProdutoRepository.save(produto);
		} else {
			throw new CustomException("Categoria já cadastrada.");
		}	
		
		CategoriaProdutoDto dto = new CategoriaProdutoDto();
		dto.setId(pd.getId());
		dto.setNomeCategoria(pd.getNomeCategoria());
		dto.setEmpresa(pd.getEmpresa().getId().toString());
		
		return dto;
	}
	
}
