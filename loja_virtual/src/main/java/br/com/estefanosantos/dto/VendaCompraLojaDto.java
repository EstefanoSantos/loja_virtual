package br.com.estefanosantos.dto;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class VendaCompraLojaDto implements Serializable {

	private static final long serialVersionUID = 1L;
	
	private Long id;

	private BigDecimal valorTotal;

	private BigDecimal valorDesconto;

	private Date dataEntrega;

	private Long enderecoEntrega;

	List<ItemVendaDto> itensVenda = new ArrayList<ItemVendaDto>();

	
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public List<ItemVendaDto> getItensVenda() {
		return itensVenda;
	}

	public void setItensVenda(List<ItemVendaDto> itensVenda) {
		this.itensVenda = itensVenda;
	}

	public BigDecimal getValorTotal() {
		return valorTotal;
	}

	public void setValorTotal(BigDecimal valorTotal) {
		this.valorTotal = valorTotal;
	}

	public BigDecimal getValorDesconto() {
		return valorDesconto;
	}

	public void setValorDesconto(BigDecimal valorDesconto) {
		this.valorDesconto = valorDesconto;
	}

	public Date getDataEntrega() {
		return dataEntrega;
	}

	public void setDataEntrega(Date dataEntrega) {
		this.dataEntrega = dataEntrega;
	}

	public Long getEnderecoEntrega() {
		return enderecoEntrega;
	}

	public void setEnderecoEntrega(Long enderecoEntrega) {
		this.enderecoEntrega = enderecoEntrega;
	}

}
