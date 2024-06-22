package br.com.estefanosantos.model;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;
import java.util.Objects;

import jakarta.persistence.Column;
import jakarta.persistence.ConstraintMode;
import jakarta.persistence.Entity;
import jakarta.persistence.ForeignKey;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.SequenceGenerator;
import jakarta.persistence.Table;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;

@Entity
@Table(name = "cupom_desconto")
@SequenceGenerator(name = "seq_cup_desconto", sequenceName = "seq_cup_desconto", initialValue = 1, allocationSize = 1)
public class CupDesconto implements Serializable {
	
	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "seq_cup_desconto")
	private Long id;
	
	@Column(nullable = false)
	private String codigoDescricao;
	
	private BigDecimal valorRealDesc;
	
	private BigDecimal valorPorcentagemDesc;
	
	@Column(nullable = false)
	@Temporal(TemporalType.DATE)
	private Date dataValidadeCupom;
	
	@ManyToOne(targetEntity = Pessoa.class)
	@JoinColumn(name = "empresa_id", nullable = false, foreignKey = @ForeignKey(value = ConstraintMode.CONSTRAINT, name = "empresa_id_fk"))
	private Pessoa empresa;

	@Override
	public int hashCode() {
		return Objects.hash(id);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		CupDesconto other = (CupDesconto) obj;
		return Objects.equals(id, other.id);
	}

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

	public BigDecimal getValorRealDesc() {
		return valorRealDesc;
	}

	public void setValorRealDesc(BigDecimal valorRealDesc) {
		this.valorRealDesc = valorRealDesc;
	}

	public BigDecimal getValorPorcentagemDesc() {
		return valorPorcentagemDesc;
	}

	public void setValorPorcentagemDesc(BigDecimal valorPorcentagemDesc) {
		this.valorPorcentagemDesc = valorPorcentagemDesc;
	}

	public Date getDataValidadeCupom() {
		return dataValidadeCupom;
	}

	public void setDataValidadeCupom(Date dataValidadeCupom) {
		this.dataValidadeCupom = dataValidadeCupom;
	}

	public Pessoa getEmpresa() {
		return empresa;
	}

	public void setEmpresa(Pessoa empresa) {
		this.empresa = empresa;
	}
	
	

}
