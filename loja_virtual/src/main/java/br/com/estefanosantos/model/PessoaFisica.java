package br.com.estefanosantos.model;

import java.util.Date;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.PrimaryKeyJoinColumn;
import jakarta.persistence.Table;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "pessoa_fisica")
@PrimaryKeyJoinColumn(name = "id")
public class PessoaFisica extends Pessoa {

	private static final long serialVersionUID = 1L;
	
	@Column(nullable = false)
	private String cpf;
	
	@Temporal(TemporalType.DATE)
	private Date dataNascimento;

}
