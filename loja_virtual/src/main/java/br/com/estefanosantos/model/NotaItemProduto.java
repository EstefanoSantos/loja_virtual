package br.com.estefanosantos.model;

import java.io.Serializable;
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
import jakarta.validation.constraints.NotNull;

@Entity
@Table(name = "nota_item_produto")
@SequenceGenerator(name = "seq_nota_item_produto", sequenceName = "seq_nota_item_produto", initialValue = 1, allocationSize = 1)
public class NotaItemProduto implements Serializable {

	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "seq_nota_item_produto")
	private Long id;
	
	@NotNull(message = "Informe a quantidade do produto.")
	@Column(nullable = false)
	private double quantidade;
	
	@NotNull(message = "Informe o produto.")
	@ManyToOne
	@JoinColumn(name = "produto_id", nullable = false,
			foreignKey = @ForeignKey(value = ConstraintMode.CONSTRAINT, name = "produto_fk"))
	private Produto produto;
	
	@NotNull(message = "Informe a nota fiscal da compra.")
	@ManyToOne
	@JoinColumn(name = "nota_fiscal_compra_id", nullable = false,
			foreignKey = @ForeignKey(value = ConstraintMode.CONSTRAINT, name = "nota_fiscal_compra_fk"))
	private NotaFiscalCompra notaFiscalCompra;
	
	@NotNull(message = "Informe a empresa.")
	@ManyToOne(targetEntity = PessoaJuridica.class)
	@JoinColumn(name = "empresa_id", nullable = false, foreignKey = @ForeignKey(value = ConstraintMode.CONSTRAINT, name = "empresa_id_fk"))
	private PessoaJuridica empresa;

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
		NotaItemProduto other = (NotaItemProduto) obj;
		return Objects.equals(id, other.id);
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public double getQuantidade() {
		return quantidade;
	}

	public void setQuantidade(double quantidade) {
		this.quantidade = quantidade;
	}

	public Produto getProduto() {
		return produto;
	}

	public void setProduto(Produto produto) {
		this.produto = produto;
	}

	public NotaFiscalCompra getNotaFiscalCompra() {
		return notaFiscalCompra;
	}

	public void setNotaFiscalCompra(NotaFiscalCompra notaFiscalCompra) {
		this.notaFiscalCompra = notaFiscalCompra;
	}

	public PessoaJuridica getEmpresa() {
		return empresa;
	}

	public void setEmpresa(PessoaJuridica empresa) {
		this.empresa = empresa;
	}
	
	
}
