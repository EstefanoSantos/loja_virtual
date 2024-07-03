package br.com.estefanosantos.model;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;
import java.util.Objects;

import br.com.estefanosantos.enums.StatusContaPagar;
import jakarta.persistence.Column;
import jakarta.persistence.ConstraintMode;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
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
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

@Entity
@Table(name = "conta_pagar")
@SequenceGenerator(name = "seq_conta_pagar", sequenceName = "seq_conta_pagar", initialValue = 1, allocationSize = 1)
public class ContaPagar implements Serializable {

	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "seq_conta_pagar")
	private Long id;
	
	@NotBlank(message = "Informe a descrição da conta a ser paga.")
	@NotNull(message = "Informe a descrição da conta a ser paga.")
	@Column(nullable = false)
	private String descricao;
	
	@NotNull(message = "Informe o valor total da conta a ser paga.")
	@Column(nullable = false)
	private BigDecimal valorTotal;
	
	private BigDecimal valorDesconto;
	
	@NotNull(message = "Informe a data de vencimento da conta a ser paga.")
	@Column(nullable = false)
	@Temporal(TemporalType.DATE)
	private Date dataVencimento;
	
	@Temporal(TemporalType.DATE)
	private Date dataPagamento;
	
	@NotNull(message = "Informe o status da conta a ser paga.")
	@Column(nullable = false)
	@Enumerated(EnumType.STRING)
	private StatusContaPagar statusContaPagar;
	
	@NotNull(message = "Pessoa responsável deve ser informada.")
	@ManyToOne(targetEntity = PessoaFisica.class)
	@JoinColumn(name = "pessoa_id", nullable = false, foreignKey = @ForeignKey(value = ConstraintMode.CONSTRAINT, name = "pessoa_fk"))
	private PessoaFisica pessoa;
	
	@NotNull(message = "Empresa responsável deve ser informada.")
	@ManyToOne(targetEntity = PessoaJuridica.class)
	@JoinColumn(name = "empresa_id", nullable = false, foreignKey = @ForeignKey(value = ConstraintMode.CONSTRAINT, name = "empresa_id_fk"))
	private PessoaJuridica empresa;
	
	@NotNull(message = "Pessoa Fornecedora responsável deve ser informada.")
	@ManyToOne(targetEntity = PessoaJuridica.class)
	@JoinColumn(name = "pessoa_fornecedora_id", nullable = false, foreignKey = @ForeignKey(value = ConstraintMode.CONSTRAINT, name = "pessoa_fornecedora_fk"))
	private PessoaJuridica pessoaFornecedora;
	
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
		ContaPagar other = (ContaPagar) obj;
		return Objects.equals(id, other.id);
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
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

	public Date getDataVencimento() {
		return dataVencimento;
	}

	public void setDataVencimento(Date dataVencimento) {
		this.dataVencimento = dataVencimento;
	}

	public Date getDataPagamento() {
		return dataPagamento;
	}

	public void setDataPagamento(Date dataPagamento) {
		this.dataPagamento = dataPagamento;
	}

	public StatusContaPagar getStatusContaPagar() {
		return statusContaPagar;
	}

	public void setStatusContaPagar(StatusContaPagar statusContaPagar) {
		this.statusContaPagar = statusContaPagar;
	}

	public PessoaFisica getPessoa() {
		return pessoa;
	}

	public void setPessoa(PessoaFisica pessoa) {
		this.pessoa = pessoa;
	}

	public PessoaJuridica getEmpresa() {
		return empresa;
	}

	public void setEmpresa(PessoaJuridica empresa) {
		this.empresa = empresa;
	}

	public PessoaJuridica getPessoaFornecedora() {
		return pessoaFornecedora;
	}

	public void setPessoaFornecedora(PessoaJuridica pessoaFornecedora) {
		this.pessoaFornecedora = pessoaFornecedora;
	}
	
	

}
