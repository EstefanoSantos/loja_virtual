package br.com.estefanosantos.model;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Objects;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.SequenceGenerator;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "produto")
@SequenceGenerator(name = "seq_produto", sequenceName = "seq_produto", initialValue = 1, allocationSize = 1)
public class Produto implements Serializable {

	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "seq_produto")
	private Long id;
	
	private String tipoUnidade; 
	
	private String nome;
	
	private BigDecimal valor;
	
	@Column(columnDefinition = "text", length = 2000)
	private String descricao;
	
	private Boolean ativo = Boolean.TRUE;
	
	/** Nota item produto - ASSOCIAR**/
	
	private Double peso;
	
	private Double altura;
	
	private Double largura;
	
	private Double profundidade;
	
	private Integer quantidadeEstoque = 0;
	
	private Integer quantidadeEstoqueAlerta = 0;
	
	private String linkYoutube;
	
	private Boolean desejaAlerta = Boolean.FALSE;
	
	private Integer quantidadeClique = 0;

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
		Produto other = (Produto) obj;
		return Objects.equals(id, other.id);
	}
	
	
}
