package br.com.estefanosantos.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.estefanosantos.exceptions.CustomException;
import br.com.estefanosantos.model.NotaItemProduto;
import br.com.estefanosantos.repository.NotaItemProdutoRepository;
import br.com.estefanosantos.service.NotaItemProdutoService;

@Service
public class NotaItemProdutoServiceImpl implements NotaItemProdutoService{

	@Autowired
	private NotaItemProdutoRepository notaItemProdutoRepository;
	
	@Override
	public NotaItemProduto buscarPorProdutoNota(Long idProduto, Long idNota) throws CustomException {
		NotaItemProduto nota = notaItemProdutoRepository.buscarNotaItemPorProdutoNota(idProduto, idNota);
		
		if (nota == null) {
			throw new CustomException("Nota não encontrada. Verifique o id do produto e da nota fiscal da venda.");
		}
		
		return nota;
	}

	@Override
	public void salvarNotaItemProduto(NotaItemProduto notaItemProduto) throws CustomException {
		
		NotaItemProduto existeNota = notaItemProdutoRepository
				.buscarNotaItemPorProdutoNota(notaItemProduto.getProduto().getId(), notaItemProduto.getNotaFiscalCompra().getId());
		
		if (existeNota != null) {
			throw new CustomException("Nota de venda já cadastrada.");
		}
		
		notaItemProdutoRepository.save(notaItemProduto);
	}

	@Override
	public void apagarNotaItemProduto(Long id) throws CustomException {
		
		NotaItemProduto nota = notaItemProdutoRepository.findById(id).orElseThrow(() -> new CustomException("Não foi encontrado nota com esse id"));
		
		notaItemProdutoRepository.deleteById(id);
	}

}
