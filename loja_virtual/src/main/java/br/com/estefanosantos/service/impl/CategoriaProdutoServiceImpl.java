package br.com.estefanosantos.service.impl;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

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
	public CategoriaProdutoDto salvarCategoria(CategoriaProduto produto) throws CustomException {
		
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

	@Override
	public CategoriaProdutoDto atualizarCategoria(CategoriaProduto produto) throws CustomException {
		
		CategoriaProduto isCategoria = categoriaProdutoRepository.buscarPorId(produto.getId());
		
		if (isCategoria == null) {
			throw new CustomException("Não existe categoria com esse id.");
		}
		
		isCategoria.setNomeCategoria(produto.getNomeCategoria());
		
		isCategoria = categoriaProdutoRepository.save(isCategoria);
		
		CategoriaProdutoDto dto = new CategoriaProdutoDto();
		dto.setId(isCategoria.getId());
		dto.setNomeCategoria(isCategoria.getNomeCategoria());
		dto.setEmpresa(isCategoria.getEmpresa().getId().toString());
		
		return dto;		
	}

	@Override
	public void deletarCategoria(Long id) throws CustomException {
		
		boolean isCategoria = categoriaProdutoRepository.existsById(id);
		
		if (isCategoria) {
			categoriaProdutoRepository.deleteById(id);
		} else {
			throw new CustomException("Não existe categoria cadastrada com esse id.");
		}
		
	}

	@Override
	public CategoriaProdutoDto selecionarCategoria(Long id) throws CustomException {
		CategoriaProduto produto = categoriaProdutoRepository.buscarPorId(id);
		
		if (produto == null) {
			throw new CustomException("Não existe categoria com esse id.");
		}
		
		CategoriaProdutoDto dto = new CategoriaProdutoDto();
		dto.setId(produto.getId());
		dto.setNomeCategoria(produto.getNomeCategoria());
		dto.setEmpresa(produto.getEmpresa().getId().toString());
		
		return dto;
	}

	@Override
	public List<CategoriaProdutoDto> selecionarTodos() {
		
		List<CategoriaProduto> lista = categoriaProdutoRepository.findAll();
		
		if (lista != null) {
			return lista.stream().map(categoria -> {
				CategoriaProdutoDto dto = new CategoriaProdutoDto();
				dto.setId(categoria.getId());
				dto.setNomeCategoria(categoria.getNomeCategoria());
				dto.setEmpresa(categoria.getEmpresa().getId().toString());
				return dto;
			}).collect(Collectors.toList());
		} else {
			return Collections.emptyList();
		}
	}
	
	
	
}
