package br.com.estefanosantos.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.estefanosantos.dto.ItemVendaDto;
import br.com.estefanosantos.dto.VendaCompraLojaDto;
import br.com.estefanosantos.exceptions.CustomException;
import br.com.estefanosantos.model.ItemVenda;
import br.com.estefanosantos.model.VendaCompraLoja;
import br.com.estefanosantos.repository.EnderecoRepository;
import br.com.estefanosantos.repository.FormaPagamentoRepository;
import br.com.estefanosantos.repository.NotaFiscalVendaRepository;
import br.com.estefanosantos.repository.PessoaFisicaRepository;
import br.com.estefanosantos.repository.PessoaJuridicaRepository;
import br.com.estefanosantos.repository.VendaCompraLojaRepository;
import br.com.estefanosantos.service.VendaCompraLojaService;

@Service
public class VendaCompraLojaServiceImpl implements VendaCompraLojaService {
	
	@Autowired
	private VendaCompraLojaRepository vendaCompraLojaRepository;
	
	@Autowired
	private FormaPagamentoRepository formaPagamentoRepository;
	
	@Autowired
	private NotaFiscalVendaRepository notaFiscalVendaRepository;
	
	@Autowired
	private PessoaFisicaRepository fisicaRepository;
	
	@Autowired
	private PessoaJuridicaRepository pessoaJuridicaRepository;
	
	@Autowired
	private EnderecoRepository enderecoRepository;

	@Override
	public VendaCompraLojaDto salvarVendaCompraLoja(VendaCompraLoja vendaCompraLoja) throws CustomException {
	
		if (!formaPagamentoRepository.existsById(vendaCompraLoja.getFormaPagamento().getId())) {
			throw new CustomException("Não foi encontrada forma de pagamento com o id fornecido: " +vendaCompraLoja.getFormaPagamento().getId());
		}
		
		if (!fisicaRepository.existsById(vendaCompraLoja.getPessoa().getId())) {
			throw new CustomException("Não foi encontrada pessoa com o id fornecido: " +vendaCompraLoja.getPessoa().getId());
		}
		
		if (!pessoaJuridicaRepository.existsById(vendaCompraLoja.getEmpresa().getId())) {
			throw new CustomException("Não foi encontrada empresa com o id fornecido: " + vendaCompraLoja.getEmpresa().getId());
		} 
		
		if (!enderecoRepository.existsById(vendaCompraLoja.getEnderecoEntrega().getId())) {
			throw new CustomException("Não foi encontrado endereço de entrega com o id fornecido: " + vendaCompraLoja.getEnderecoEntrega().getId());
		}
		
		if (!enderecoRepository.existsById(vendaCompraLoja.getEnderecoCobranca().getId())) {
			throw new CustomException("Não foi encontrado endereço de cobrança com o id: " + vendaCompraLoja.getEnderecoCobranca().getId());
		}
		
		for (int i = 0; i < vendaCompraLoja.getItemVenda().size(); i++) {
			vendaCompraLoja.getItemVenda().get(i).setEmpresa(vendaCompraLoja.getEmpresa());
			vendaCompraLoja.getItemVenda().get(i).setVendaCompraLoja(vendaCompraLoja);
		}
		
		vendaCompraLoja = vendaCompraLojaRepository.save(vendaCompraLoja);
		
		vendaCompraLoja.getNotaFiscalVenda().setVendaCompraLoja(vendaCompraLoja);
		
		notaFiscalVendaRepository.save(vendaCompraLoja.getNotaFiscalVenda());
		
		VendaCompraLojaDto dto = new VendaCompraLojaDto();
		
		dto.setValorTotal(vendaCompraLoja.getValorTotal());
		dto.setValorDesconto(vendaCompraLoja.getValorDesconto());
		dto.setDataEntrega(vendaCompraLoja.getDataEntrega());
		
		ItemVendaDto dtoItemVenda = new ItemVendaDto();
		for (int i = 0; i < vendaCompraLoja.getItemVenda().size(); i++) {
			dtoItemVenda.setQuantidade(vendaCompraLoja.getItemVenda().get(i).getQuantidade());
			dtoItemVenda.setProduto(vendaCompraLoja.getItemVenda().get(i).getProduto());
			dto.getItensVenda().add(dtoItemVenda);
		}		
		
		return dto;
	}

	@Override
	public VendaCompraLojaDto buscarVendaPorId(Long id) throws CustomException {
		
		VendaCompraLoja compra = vendaCompraLojaRepository.findById(id).orElseThrow(
				() -> new CustomException("Não foi encontrada venda com id informado: " + id));
		
		VendaCompraLojaDto compraDto = new VendaCompraLojaDto();
		
		compraDto.setId(compra.getId());
		compraDto.setValorTotal(compra.getValorTotal());
		compraDto.setValorDesconto(compra.getValorDesconto());
		compraDto.setDataEntrega(compra.getDataEntrega());
		compraDto.setEnderecoEntrega(compra.getEnderecoEntrega().getId());
		
		for (ItemVenda venda : compra.getItemVenda()) {
			
			ItemVendaDto itemDto = new ItemVendaDto();
			
			itemDto.setProduto(venda.getProduto());
			itemDto.setQuantidade(venda.getQuantidade());
			
			compraDto.getItensVenda().add(itemDto);
			
		}
		
		return compraDto;
		
	}

}
