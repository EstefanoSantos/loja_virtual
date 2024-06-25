package br.com.estefanosantos.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.PrimaryKeyJoinColumn;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotBlank;

@Entity
@Table(name = "pessoa_juridica")
@PrimaryKeyJoinColumn(name = "id")
public class PessoaJuridica extends Pessoa {

	private static final long serialVersionUID = 1L;
	
	@NotBlank(message = "Insira um Cnpj.")
	@Column(nullable = false)
	private String cnpj;
	
	@NotBlank(message = "Insira a Inscrição Estadual.")
	@Column(nullable = false)
	private String inscricaoEstadual;
	
	@NotBlank(message = "Insira a inscrição Municipal.")
	@Column(nullable = false)
	private String inscricaoMunicipal;
	
	@NotBlank(message = "Insira o nome fantasia da empresa.")
	@Column(nullable = false)
	private String nomeFantasia;
	
	@NotBlank(message = "Insira a razão social da empresa.")
	@Column(nullable = false)
	private String razaoSocial;
	
	@NotBlank(message = "Insira a categoria da empresa.")
	@Column(nullable = false)
	private String categoria;

	public String getCnpj() {
		return cnpj;
	}

	public void setCnpj(String cnpj) {
		this.cnpj = cnpj;
	}

	public String getInscricaoEstadual() {
		return inscricaoEstadual;
	}

	public void setInscricaoEstadual(String inscricaoEstadual) {
		this.inscricaoEstadual = inscricaoEstadual;
	}

	public String getInscricaoMunicipal() {
		return inscricaoMunicipal;
	}

	public void setInscricaoMunicipal(String inscricaoMunicipal) {
		this.inscricaoMunicipal = inscricaoMunicipal;
	}

	public String getNomeFantasia() {
		return nomeFantasia;
	}

	public void setNomeFantasia(String nomeFantasia) {
		this.nomeFantasia = nomeFantasia;
	}

	public String getRazaoSocial() {
		return razaoSocial;
	}

	public void setRazaoSocial(String razaoSocial) {
		this.razaoSocial = razaoSocial;
	}

	public String getCategoria() {
		return categoria;
	}

	public void setCategoria(String categoria) {
		this.categoria = categoria;
	}
	
	
}
