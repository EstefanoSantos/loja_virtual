package br.com.estefanosantos.dto;

import java.io.Serializable;
import java.math.BigDecimal;

public class CupDescontoDto implements Serializable {

	private static final long serialVersionUID = 1L;

	private Long id;

	private String codigoDescricao;

	private BigDecimal valorRealDes;

	private BigDecimal valorPorcentagemDesc;

	private Long idEmpresa;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getCodigoDescricao() {
		return codigoDescricao;
	}

	public void setCodigoDescricao(String codigoDescricao) {
		this.codigoDescricao = codigoDescricao;
	}

	public BigDecimal getValorRealDes() {
		return valorRealDes;
	}

	public void setValorRealDes(BigDecimal valorRealDes) {
		this.valorRealDes = valorRealDes;
	}

	public BigDecimal getValorPorcentagemDesc() {
		return valorPorcentagemDesc;
	}

	public void setValorPorcentagemDesc(BigDecimal valorPorcentagemDesc) {
		this.valorPorcentagemDesc = valorPorcentagemDesc;
	}

	public Long getIdEmpresa() {
		return idEmpresa;
	}

	public void setIdEmpresa(Long idEmpresa) {
		this.idEmpresa = idEmpresa;
	}

}
