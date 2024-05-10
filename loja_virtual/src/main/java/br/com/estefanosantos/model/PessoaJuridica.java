package br.com.estefanosantos.model;

import jakarta.persistence.Entity;
import jakarta.persistence.PrimaryKeyJoinColumn;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "pessoa_juridica")
@PrimaryKeyJoinColumn(name = "id")
public class PessoaJuridica extends Pessoa {

	private static final long serialVersionUID = 1L;
	
	
	private String cnpj;
	
	private String inscricaoEstadual;
	
	private String inscricaoMunicipal;
	
	private String nomeFantasia;
	
	private String razaoSocial;
	
	private String categoria;
}
