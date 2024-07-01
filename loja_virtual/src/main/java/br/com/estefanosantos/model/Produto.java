package br.com.estefanosantos.model;

import java.io.Serializable;
import java.math.BigDecimal;
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
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

@Entity
@Table(name = "produto")
@SequenceGenerator(name = "seq_produto", sequenceName = "seq_produto", initialValue = 1, allocationSize = 1)
public class Produto implements Serializable {

	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "seq_produto")
	private Long id;
	
	@NotNull(message = "Informe o tipo de unidade do produto.")
	@NotBlank(message = "Informe o tipo de unidade do produto")
	@Column(nullable = false)
	private String tipoUnidade; 
	
	@Size(min = 10, message = "Nome do produto deve ter mais de 10 letras.")
	@NotNull(message = "Informe o nome do produto.")
	@NotBlank(message = "Informe o nome do produto")
	@Column(nullable = false)
	private String nome;
	
	@NotNull(message = "Informe o valor do produto.")
	@Column(nullable = false)
	private BigDecimal valor;
	
	@NotNull(message = "Informe a descrição do produto.")
	@NotBlank(message = "Informe a descrição do produto")
	@Column(columnDefinition = "text", length = 2000, nullable = false)
	private String descricao;
	
	@Column(nullable = false)
	private Boolean ativo = Boolean.TRUE;
	
	/** Nota item produto - ASSOCIAR**/
	
	@NotNull(message = "Informe o peso do produto.")
	@Column(nullable = false)
	private Double peso;
	
	@NotNull(message = "Informe a altura do produto.")
	@Column(nullable = false)
	private Double altura;
	
	@NotNull(message = "Informe a largura do produto.")
	@Column(nullable = false)
	private Double largura;
	
	@NotNull(message = "Informe a profundidade do produto.")
	@Column(nullable = false)
	private Double profundidade;
	
	@NotNull(message = "Informe a quantidade em estoque do produto.")
	@Column(nullable = false)
	private Integer quantidadeEstoque = 0;
	
	private Integer quantidadeEstoqueAlerta = 0;
	
	private String linkYoutube;
	
	private Boolean desejaAlerta = Boolean.FALSE;
	
	private Integer quantidadeClique = 0;
	
	@NotNull(message = "A empresa responsável deve ser informada.")
	@ManyToOne(targetEntity = Pessoa.class)
	@JoinColumn(name = "empresa_id", nullable = false, foreignKey = @ForeignKey(value = ConstraintMode.CONSTRAINT, name = "empresa_id_fk"))
	private PessoaJuridica empresa;
	
	@NotNull(message = "A categoria do produto deve ser informada.")
	@ManyToOne(targetEntity = CategoriaProduto.class)
	@JoinColumn(name = "categoria_produto_id", nullable = false, foreignKey = @ForeignKey(value = ConstraintMode.CONSTRAINT, name = "categoria_produto_id_fk"))
	private CategoriaProduto categoriaProduto;
	
	@NotNull(message = "A marca do produto deve ser informada.")
	@ManyToOne(targetEntity = MarcaProduto.class)
	@JoinColumn(name = "marca_produto_id", nullable = false, foreignKey = @ForeignKey(value = ConstraintMode.CONSTRAINT, name = "marca_produto_id_fk"))
	private MarcaProduto marcaProduto;
	
	
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

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getTipoUnidade() {
		return tipoUnidade;
	}

	public void setTipoUnidade(String tipoUnidade) {
		this.tipoUnidade = tipoUnidade;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public BigDecimal getValor() {
		return valor;
	}

	public void setValor(BigDecimal valor) {
		this.valor = valor;
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	public Boolean getAtivo() {
		return ativo;
	}

	public void setAtivo(Boolean ativo) {
		this.ativo = ativo;
	}

	public Double getPeso() {
		return peso;
	}

	public void setPeso(Double peso) {
		this.peso = peso;
	}

	public Double getAltura() {
		return altura;
	}

	public void setAltura(Double altura) {
		this.altura = altura;
	}

	public Double getLargura() {
		return largura;
	}

	public void setLargura(Double largura) {
		this.largura = largura;
	}

	public Double getProfundidade() {
		return profundidade;
	}

	public void setProfundidade(Double profundidade) {
		this.profundidade = profundidade;
	}

	public Integer getQuantidadeEstoque() {
		return quantidadeEstoque;
	}

	public void setQuantidadeEstoque(Integer quantidadeEstoque) {
		this.quantidadeEstoque = quantidadeEstoque;
	}

	public Integer getQuantidadeEstoqueAlerta() {
		return quantidadeEstoqueAlerta;
	}

	public void setQuantidadeEstoqueAlerta(Integer quantidadeEstoqueAlerta) {
		this.quantidadeEstoqueAlerta = quantidadeEstoqueAlerta;
	}

	public String getLinkYoutube() {
		return linkYoutube;
	}

	public void setLinkYoutube(String linkYoutube) {
		this.linkYoutube = linkYoutube;
	}

	public Boolean getDesejaAlerta() {
		return desejaAlerta;
	}

	public void setDesejaAlerta(Boolean desejaAlerta) {
		this.desejaAlerta = desejaAlerta;
	}

	public Integer getQuantidadeClique() {
		return quantidadeClique;
	}

	public void setQuantidadeClique(Integer quantidadeClique) {
		this.quantidadeClique = quantidadeClique;
	}

	public PessoaJuridica getEmpresa() {
		return empresa;
	}

	public void setEmpresa(PessoaJuridica empresa) {
		this.empresa = empresa;
	}
	
	public void setCategoriaProduto(CategoriaProduto categoriaProduto) {
		this.categoriaProduto = categoriaProduto;
	}
	
	public CategoriaProduto getCategoriaProduto() {
		return this.categoriaProduto;
	}
	
	public void setMarcaProduto(MarcaProduto marcaProduto) {
		this.marcaProduto = marcaProduto;
	}
	
	public MarcaProduto getMarcaProduto() {
		return marcaProduto;
	}
	
	
}
