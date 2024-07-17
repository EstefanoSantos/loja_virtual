package br.com.estefanosantos.strategies;

import java.util.HashMap;
import java.util.Map;

import org.springframework.stereotype.Component;

import br.com.estefanosantos.exceptions.CustomException;
import br.com.estefanosantos.repository.VendaCompraLojaRepository;
import br.com.estefanosantos.strategies.vendas.VendaBuscarPorCpfStrategy;
import br.com.estefanosantos.strategies.vendas.VendaBuscarPorEmpresaIdStrategy;
import br.com.estefanosantos.strategies.vendas.VendaBuscarPorFormaPagamentoStrategy;
import br.com.estefanosantos.strategies.vendas.VendaBuscarPorNomeClienteStrategy;
import br.com.estefanosantos.strategies.vendas.VendaBuscarPorNomeProdutoStrategy;
import br.com.estefanosantos.strategies.vendas.VendaBuscarPorPessoaIdStrategy;
import br.com.estefanosantos.strategies.vendas.VendaBuscarPorProdutoStrategy;

@Component
public class VendaBuscarStrategyFactory {
	
	private Map<String, VendaCompraLojaStrategy> strategies;	
	
	public VendaBuscarStrategyFactory(VendaCompraLojaRepository repository) {
		
		strategies = new HashMap<String, VendaCompraLojaStrategy>();
		
		strategies.put("POR_PESSOA_ID", new VendaBuscarPorPessoaIdStrategy(repository));
		strategies.put("POR_EMPRESA_ID", new VendaBuscarPorEmpresaIdStrategy(repository));
		strategies.put("POR_FORMA_PAGAMENTO", new VendaBuscarPorFormaPagamentoStrategy(repository));
		strategies.put("POR_PRODUTO_ID", new VendaBuscarPorProdutoStrategy(repository));
		strategies.put("POR_NOME_PRODUTO", new VendaBuscarPorNomeProdutoStrategy(repository));
		strategies.put("POR_NOME_CLIENTE", new VendaBuscarPorNomeClienteStrategy(repository));
		strategies.put("POR_CPF", new VendaBuscarPorCpfStrategy(repository));
		
	}	
	
	public VendaCompraLojaStrategy getStrategy(String parametro) throws CustomException {
		
		VendaCompraLojaStrategy strategy = strategies.get(parametro.toUpperCase());
		
		if (strategy == null) {
			throw new CustomException("Informe o parâmetro de buscar correto.");
		}
		
		return strategy;
	}

}
