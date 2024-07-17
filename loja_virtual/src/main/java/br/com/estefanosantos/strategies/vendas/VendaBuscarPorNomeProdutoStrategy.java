package br.com.estefanosantos.strategies.vendas;

import java.util.List;

import br.com.estefanosantos.model.VendaCompraLoja;
import br.com.estefanosantos.repository.VendaCompraLojaRepository;
import br.com.estefanosantos.strategies.VendaCompraLojaStrategy;

public class VendaBuscarPorNomeProdutoStrategy implements VendaCompraLojaStrategy {

	private VendaCompraLojaRepository repository;

	public VendaBuscarPorNomeProdutoStrategy(VendaCompraLojaRepository repository) {
		this.repository = repository;
	}

	@Override
	public List<VendaCompraLoja> buscar(String valor) {
		return repository.buscarPorNomeProduto(valor.toUpperCase().trim());
	}

}