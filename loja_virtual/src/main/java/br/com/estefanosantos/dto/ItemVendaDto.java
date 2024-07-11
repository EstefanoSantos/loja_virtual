package br.com.estefanosantos.dto;

import br.com.estefanosantos.model.Produto;

public class ItemVendaDto {
	
	private Double quantidade;
	
	private Produto produto;

	public Double getQuantidade() {
		return quantidade;
	}

	public void setQuantidade(Double quantidade) {
		this.quantidade = quantidade;
	}

	public Produto getProduto() {
		return produto;
	}

	public void setProduto(Produto produto) {
		this.produto = produto;
	}
	
	
}
