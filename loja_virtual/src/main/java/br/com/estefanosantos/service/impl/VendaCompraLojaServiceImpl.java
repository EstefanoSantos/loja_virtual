package br.com.estefanosantos.service.impl;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

import br.com.estefanosantos.dto.ItemVendaDto;
import br.com.estefanosantos.dto.VendaCompraLojaDto;
import br.com.estefanosantos.exceptions.CustomException;
import br.com.estefanosantos.model.ItemVenda;
import br.com.estefanosantos.model.StatusRastreio;
import br.com.estefanosantos.model.VendaCompraLoja;
import br.com.estefanosantos.repository.EnderecoRepository;
import br.com.estefanosantos.repository.FormaPagamentoRepository;
import br.com.estefanosantos.repository.NotaFiscalVendaRepository;
import br.com.estefanosantos.repository.PessoaFisicaRepository;
import br.com.estefanosantos.repository.PessoaJuridicaRepository;
import br.com.estefanosantos.repository.StatusRastreioRepository;
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

	@Autowired
	private StatusRastreioRepository statusRastreioRepository;

	@Autowired
	private JdbcTemplate jdbcTemplate;

	@Override
	public VendaCompraLojaDto salvarVendaCompraLoja(VendaCompraLoja vendaCompraLoja) throws CustomException {

		if (!formaPagamentoRepository.existsById(vendaCompraLoja.getFormaPagamento().getId())) {
			throw new CustomException("Não foi encontrada forma de pagamento com o id fornecido: "
					+ vendaCompraLoja.getFormaPagamento().getId());
		}

		if (!fisicaRepository.existsById(vendaCompraLoja.getPessoa().getId())) {
			throw new CustomException(
					"Não foi encontrada pessoa com o id fornecido: " + vendaCompraLoja.getPessoa().getId());
		}

		if (!pessoaJuridicaRepository.existsById(vendaCompraLoja.getEmpresa().getId())) {
			throw new CustomException(
					"Não foi encontrada empresa com o id fornecido: " + vendaCompraLoja.getEmpresa().getId());
		}

		if (!enderecoRepository.existsById(vendaCompraLoja.getEnderecoEntrega().getId())) {
			throw new CustomException("Não foi encontrado endereço de entrega com o id fornecido: "
					+ vendaCompraLoja.getEnderecoEntrega().getId());
		}

		if (!enderecoRepository.existsById(vendaCompraLoja.getEnderecoCobranca().getId())) {
			throw new CustomException("Não foi encontrado endereço de cobrança com o id: "
					+ vendaCompraLoja.getEnderecoCobranca().getId());
		}

		for (int i = 0; i < vendaCompraLoja.getItemVenda().size(); i++) {
			vendaCompraLoja.getItemVenda().get(i).setEmpresa(vendaCompraLoja.getEmpresa());
			vendaCompraLoja.getItemVenda().get(i).setVendaCompraLoja(vendaCompraLoja);
		}

		vendaCompraLoja = vendaCompraLojaRepository.save(vendaCompraLoja);

		vendaCompraLoja.getNotaFiscalVenda().setVendaCompraLoja(vendaCompraLoja);

		notaFiscalVendaRepository.save(vendaCompraLoja.getNotaFiscalVenda());

		StatusRastreio statusRastreio = new StatusRastreio();

		statusRastreio.setCentroDistribuicao("São Paulo");
		statusRastreio.setCidade("São Paulo");
		statusRastreio.setEmpresa(vendaCompraLoja.getEmpresa());
		statusRastreio.setEstado("SP");
		statusRastreio.setStatus("Confirmando venda.");
		statusRastreio.setVendaCompraLoja(vendaCompraLoja);

		statusRastreio = statusRastreioRepository.save(statusRastreio);

		VendaCompraLojaDto dto = new VendaCompraLojaDto();

		dto.setValorTotal(vendaCompraLoja.getValorTotal());
		dto.setValorDesconto(vendaCompraLoja.getValorDesconto());
		dto.setDataEntrega(vendaCompraLoja.getDataEntrega());
		dto.setIdStatusRastreio(statusRastreio.getId());

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

		VendaCompraLoja compra = vendaCompraLojaRepository.findById(id)
				.orElseThrow(() -> new CustomException("Não foi encontrada venda com id informado: " + id));

		if (compra.getExcluido() == true) {
			throw new CustomException("Não foi encontrada venda com id informado: " + id);
		}

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

	@Override
	public void excluirVendaTotal(Long id) throws CustomException, SQLException {

		Boolean existeVenda = vendaCompraLojaRepository.existsById(id);

		if (!existeVenda) {
			throw new CustomException("Não foi encontrada venda com o id fornecido: " + id);
		}

		String query = "begin; " + " delete from item_venda where venda_compra_loja_id = " + id + "; "
				+ " delete from status_rastreio where venda_compra_loja_id = " + id + "; "
				+ " UPDATE nota_fiscal_venda set venda_compra_loja_id = null where venda_compra_loja_id = " + id + "; "
				+ " delete from nota_fiscal_venda where venda_compra_loja_id = " + id + "; "
				+ " delete from venda_compra_loja where id = " + id + "; " + " commit; ";

		jdbcTemplate.execute(query);
	}

	@Override
	public void esconderVendaTotal(Long id) throws CustomException, SQLException {

		VendaCompraLoja compra = vendaCompraLojaRepository.findById(id)
				.orElseThrow(() -> new CustomException("Não foi encontrada venda com id informado: " + id));

		if (compra.getExcluido() == true) {
			throw new CustomException("Não foi encontrada venda com id informado: " + id);
		}

		String query = "begin; UPDATE venda_compra_loja set excluido = true where id = " + id + "; commit;";

		jdbcTemplate.execute(query);

	}

	@Override
	public List<VendaCompraLojaDto> buscarVendaDinamica(String valor, String tipoConsulta) throws CustomException {
		
		
		List<VendaCompraLoja> vendas = null; 
		
		if (tipoConsulta.equalsIgnoreCase("POR_PESSOA_ID")) {
			
			vendas = vendaCompraLojaRepository.buscarPorPessoa(Long.parseLong(valor));
			
		} else if (tipoConsulta.equalsIgnoreCase("POR_EMPRESA_ID")) {
			
			vendas = vendaCompraLojaRepository.buscarPorEmpresa(Long.parseLong(valor));
			
		} else if (tipoConsulta.equalsIgnoreCase("POR_FORMA_PAGAMENTO")) {
			
			vendas = vendaCompraLojaRepository.buscarPorFormaDePagamento(Long.parseLong(valor));
			
		} else if (tipoConsulta.equalsIgnoreCase("POR_PRODUTO_ID")) {
			
			vendas = vendaCompraLojaRepository.buscarPorProduto(Long.parseLong(valor));
			
		} else if (tipoConsulta.equalsIgnoreCase("POR_NOME_PRODUTO")) {
			
			vendas = vendaCompraLojaRepository.buscarPorNomeProduto(valor.toUpperCase().trim());
			
		} else if (tipoConsulta.equalsIgnoreCase("POR_NOME_CLIENTE")) {
			
			vendas= vendaCompraLojaRepository.buscarPorNomeCliente(valor.toUpperCase().trim());
			
		} else {
			
			throw new CustomException("Informe o parâmetro correto de busca.");
			
		}
		

		if (vendas.isEmpty()) {
			throw new CustomException("Não encontramos vendas com o id de produto fornecido: " + valor);
		}

		List<VendaCompraLojaDto> listaVendas = new ArrayList<VendaCompraLojaDto>();

		for (VendaCompraLoja venda : vendas) {

			VendaCompraLojaDto dto = new VendaCompraLojaDto();

			dto.setId(venda.getId());
			dto.setDataEntrega(venda.getDataEntrega());
			dto.setEnderecoEntrega(venda.getEnderecoEntrega().getId());
			dto.setValorDesconto(venda.getValorDesconto());
			dto.setValorTotal(venda.getValorTotal());

			for (ItemVenda item : venda.getItemVenda()) {

				ItemVendaDto itemDto = new ItemVendaDto();

				itemDto.setProduto(item.getProduto());
				itemDto.setQuantidade(item.getQuantidade());

				dto.getItensVenda().add(itemDto);
			}

			listaVendas.add(dto);

		}

		return listaVendas;
	}

}
