package br.com.estefanosantos.model;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;
import java.util.Objects;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.SequenceGenerator;
import jakarta.persistence.Table;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
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
	
	

}
