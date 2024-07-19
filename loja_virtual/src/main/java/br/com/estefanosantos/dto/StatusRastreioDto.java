package br.com.estefanosantos.dto;

import java.io.Serializable;

public class StatusRastreioDto implements Serializable {

	private static final long serialVersionUID = 1L;

	private Long id;

	private String centroDistribuicao;

	private String estado;

	private String cidade;

	private String status;

	private Long idVendaCompraLoja;

	private Long idEmpresa;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getCentroDistribuicao() {
		return centroDistribuicao;
	}

	public void setCentroDistribuicao(String centroDistribuicao) {
		this.centroDistribuicao = centroDistribuicao;
	}

	public String getEstado() {
		return estado;
	}

	public void setEstado(String estado) {
		this.estado = estado;
	}

	public String getCidade() {
		return cidade;
	}

	public void setCidade(String cidade) {
		this.cidade = cidade;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
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
