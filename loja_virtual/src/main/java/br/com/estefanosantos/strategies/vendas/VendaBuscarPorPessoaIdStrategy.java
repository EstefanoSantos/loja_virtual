package br.com.estefanosantos.strategies.vendas;

import java.util.List;

import br.com.estefanosantos.model.VendaCompraLoja;
import br.com.estefanosantos.repository.VendaCompraLojaRepository;
import br.com.estefanosantos.strategies.VendaCompraLojaStrategy;

public class VendaBuscarPorPessoaIdStrategy implements VendaCompraLojaStrategy {
	
	private VendaCompraLojaRepository repository;
	
	public VendaBuscarPorPessoaIdStrategy(VendaCompraLojaRepository repository) {
		this.repository = repository;
	}

	@Override
	public List<VendaCompraLoja> buscar(String valor) {
		return repository.buscarPorPessoa(Long.parseLong(valor));
	}

}
