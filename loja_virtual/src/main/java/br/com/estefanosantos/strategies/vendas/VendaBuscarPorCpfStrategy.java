package br.com.estefanosantos.strategies.vendas;

import java.util.List;

import br.com.estefanosantos.model.VendaCompraLoja;
import br.com.estefanosantos.repository.VendaCompraLojaRepository;
import br.com.estefanosantos.strategies.VendaCompraLojaStrategy;

public class VendaBuscarPorCpfStrategy implements VendaCompraLojaStrategy {
	
	private VendaCompraLojaRepository repository;
	
	public VendaBuscarPorCpfStrategy(VendaCompraLojaRepository repository) {
		this.repository = repository;
	}

	@Override
	public List<VendaCompraLoja> buscar(String valor) {
		
		String valorLimpo = valor.replaceAll("[^0-9]", "");
		
		return repository.buscarPorCpf(valorLimpo);
	}

}
