package br.com.estefanosantos.dto;

import java.io.Serializable;

public class NotaFiscalVendaDto implements Serializable{

	private static final long serialVersionUID = 1L;

	private Long id;

	private String numero;

	private String tipo;

	private String xml;

	private String pdf;

	private Long idVendaCompraLoja;

	private Long idEmpresa;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getNumero() {
		return numero;
	}

	public void setNumero(String numero) {
		this.numero = numero;
	}

	public String getTipo() {
		return tipo;
	}

	public void setTipo(String tipo) {
		this.tipo = tipo;
	}

	public String getXml() {
		return xml;
	}

	public void setXml(String xml) {
		this.xml = xml;
	}

	public String getPdf() {
		return pdf;
	}

	public void setPdf(String pdf) {
		this.pdf = pdf;
	}

	public Long getIdVendaCompraLoja() {
		return idVendaCompraLoja;
	}

	public void setIdVendaCompraLoja(Long idVendaCompraLoja) {
		this.idVendaCompraLoja = idVendaCompraLoja;
	}

	public Long getIdEmpresa() {
		return idEmpresa;
	}

	public void setIdEmpresa(Long idEmpresa) {
		this.idEmpresa = idEmpresa;
	}

}
