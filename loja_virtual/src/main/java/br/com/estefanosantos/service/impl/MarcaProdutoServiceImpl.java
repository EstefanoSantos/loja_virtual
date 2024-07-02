package br.com.estefanosantos.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.estefanosantos.exceptions.CustomException;
import br.com.estefanosantos.model.MarcaProduto;
import br.com.estefanosantos.repository.MarcaProdutoRepository;
import br.com.estefanosantos.service.MarcaProdutoService;

@Service
public class MarcaProdutoServiceImpl implements MarcaProdutoService {	
	
	@Autowired
	MarcaProdutoRepository marcaProdutoRepository;

	@Override
	public void salvarMarcaProduto(MarcaProduto marcaProduto) throws CustomException {
		
		List<MarcaProduto> produtos = marcaProdutoRepository.
				buscarMarcaNome(marcaProduto.getMarcaNome().toUpperCase(), marcaProduto.getEmpresa().getId());
		
		if (!produtos.isEmpty()) {
			throw new CustomException("Já existe cadastro de marca com o nome "
		+ marcaProduto.getMarcaNome() + " para a empresa de id " + marcaProduto.getEmpresa().getId());
		}
		
		marcaProdutoRepository.save(marcaProduto);
	}

	@Override
	public MarcaProduto buscarMarcaProduto(Long id) throws CustomException {
		
		MarcaProduto marcaProduto = marcaProdutoRepository.findById(id).orElseThrow(() 
				-> new CustomException("Não foi encontrada marca de produto com esse id."));
		
		return marcaProduto;
	}

	@Override
	public void apagarMarcaProduto(Long id) throws CustomException {
		
		MarcaProduto marcaProduto = marcaProdutoRepository.findById(id).orElseThrow(()
				-> new CustomException("Não foi encontrada marca de produto com esse id"));
		
		if (marcaProduto != null) {
			marcaProdutoRepository.deleteById(id);
		}
		
	}

	@Override
	public List<MarcaProduto> buscarProdutos() throws CustomException {
		
		List<MarcaProduto> produtos = marcaProdutoRepository.findAll();
		
		if (produtos.isEmpty()) {
			throw new CustomException("Ainda não existe marcas de produtos cadastradas.");
		}
		
		return produtos;
	}

	@Override
	public List<MarcaProduto> buscarMarcaNome(String nome) throws CustomException {
		
		List<MarcaProduto> marcas = marcaProdutoRepository.buscarMarcaNome(nome);
		
		if (marcas.isEmpty()) {
			throw new CustomException("Não foram encontrados produtos.");
		}
		
		return marcas;
	}

}
