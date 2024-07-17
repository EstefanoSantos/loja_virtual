package br.com.estefanosantos.strategies;

import java.util.List;

import br.com.estefanosantos.model.VendaCompraLoja;

public interface VendaCompraLojaStrategy {
	
	List<VendaCompraLoja> buscar(String valor);

}
