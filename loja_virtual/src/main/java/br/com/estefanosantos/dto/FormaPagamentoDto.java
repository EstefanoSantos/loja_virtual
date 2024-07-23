package br.com.estefanosantos.dto;

import java.io.Serializable;

public class FormaPagamentoDto implements Serializable{

	private static final long serialVersionUID = 1L;
	
	private Long id;
	
	private String formaPagamento;
	
	private Long idEmpresa;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getFormaPagamento() {
		return formaPagamento;
	}

	public void setFormaPagamento(String formaPagamento) {
		this.formaPagamento = formaPagamento;
	}

	public Long getIdEmpresa() {
		return idEmpresa;
	}

	public void setIdEmpresa(Long idEmpresa) {
		this.idEmpresa = idEmpresa;
	}
	
	

}
