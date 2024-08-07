package br.com.estefanosantos.controller;

import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import br.com.estefanosantos.dto.VendaCompraLojaDto;
import br.com.estefanosantos.exceptions.CustomException;
import br.com.estefanosantos.model.VendaCompraLoja;
import br.com.estefanosantos.service.VendaCompraLojaService;
import jakarta.validation.Valid;

@RestController
public class VendaCompraLojaController {

	@Autowired
	private VendaCompraLojaService vendaCompraLojaService;

	@ResponseBody
	@PostMapping("/salvarVendaCompra")
	public ResponseEntity<VendaCompraLojaDto> salvarVendaCompra(@RequestBody @Valid VendaCompraLoja vendaCompraLoja)
			throws CustomException {

		if (vendaCompraLoja == null) {
			throw new CustomException("Corpo da requisição vazio.");
		}

		if (vendaCompraLoja.getId() != null) {
			throw new CustomException("Corpo da requisição não pode possuir id.");
		}

		if (vendaCompraLoja.getFormaPagamento() == null || (vendaCompraLoja.getFormaPagamento().getId() == null
				|| vendaCompraLoja.getFormaPagamento().getId() <= 0)) {
			throw new CustomException("Informe a forma de pagamento.");
		}

		if (vendaCompraLoja.getNotaFiscalVenda() == null) {
			throw new CustomException("Informe a nota fiscal da venda.");
		}

		if (vendaCompraLoja.getPessoa() == null
				|| (vendaCompraLoja.getPessoa().getId() == null || vendaCompraLoja.getPessoa().getId() <= 0)) {
			throw new CustomException("Informe a pessoa que efetuou a compra.");
		}

		if (vendaCompraLoja.getEmpresa() == null
				|| (vendaCompraLoja.getEmpresa().getId() == null || vendaCompraLoja.getEmpresa().getId() <= 0)) {
			throw new CustomException("Informe a empresa que efetuou a venda.");
		}

		if (vendaCompraLoja.getEnderecoEntrega() == null || (vendaCompraLoja.getEnderecoEntrega().getId() == null
				|| vendaCompraLoja.getEnderecoEntrega().getId() <= 0)) {
			throw new CustomException("Informe  o endereço de entrega.");
		}

		if (vendaCompraLoja.getEnderecoCobranca() == null || (vendaCompraLoja.getEnderecoCobranca().getId() == null
				|| vendaCompraLoja.getEnderecoCobranca().getId() <= 0)) {
			throw new CustomException("Informe  o endereço de cobrança.");
		}
		
		if (vendaCompraLoja.getItemVenda().isEmpty()) {
			throw new CustomException("Informe os itens da venda.");
		}

		VendaCompraLojaDto dto = vendaCompraLojaService.salvarVendaCompraLoja(vendaCompraLoja);

		return new ResponseEntity<VendaCompraLojaDto>(dto, HttpStatus.OK);
	}
	
	@ResponseBody
	@GetMapping("/buscarVendaPorId/{idVenda}")
	public ResponseEntity<VendaCompraLojaDto> buscarVendaPorId(@PathVariable("idVenda") Long id) throws CustomException {
		
		if (id == null) {
			throw new CustomException("Informe o id da venda.");
		}
		
		VendaCompraLojaDto dto = vendaCompraLojaService.buscarVendaPorId(id);
		
		return new ResponseEntity<VendaCompraLojaDto>(dto, HttpStatus.OK);
	}
	
	@ResponseBody
	@DeleteMapping("/excluirVendaTotal/{id}")
	public ResponseEntity<String> excluirVendaTotal(@PathVariable("id") Long id) throws CustomException, SQLException {
		
		vendaCompraLojaService.excluirVendaTotal(id);
		
		return new ResponseEntity<String>("Venda removida com sucesso!", HttpStatus.OK);
	}
	
	@ResponseBody
	@PutMapping("/esconderVendaTotal/{id}")
	public ResponseEntity<String> esconderVendaTotal(@PathVariable("id") Long id) throws CustomException, SQLException {
		
		vendaCompraLojaService.esconderVendaTotal(id);
		
		return new ResponseEntity<String>("Venda excluída com sucesso!", HttpStatus.OK);
	}
	
	@ResponseBody
	@GetMapping("/buscarVendasDinamica/{valor}/{tipoConsulta}")
	public ResponseEntity<List<VendaCompraLojaDto>> buscarVendasDinicamica(@PathVariable("valor") String valor,
			@PathVariable("tipoConsulta") String tipoConsulta) throws CustomException {
		
		List<VendaCompraLojaDto> vendas = vendaCompraLojaService.buscarVendaDinamica(valor, tipoConsulta);
		
		return new ResponseEntity<List<VendaCompraLojaDto>>(vendas, HttpStatus.OK);
	}
	
	@ResponseBody
	@GetMapping("/buscarPorPeriodoData/{data1}/{data2}")
	public ResponseEntity<List<VendaCompraLojaDto>> buscarPorPeriodoData(
			@PathVariable("data1") String data1, @PathVariable("data2") String data2) throws ParseException, CustomException {
		
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
		
		Date d1 = dateFormat.parse(data1);
		
		Date d2 = dateFormat.parse(data2);
		
		List<VendaCompraLojaDto> vendas = vendaCompraLojaService.buscarPorPeriodoData(d1, d2);
		
		return new ResponseEntity<List<VendaCompraLojaDto>>(vendas, HttpStatus.OK);
	}
}
