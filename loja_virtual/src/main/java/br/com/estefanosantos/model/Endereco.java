package br.com.estefanosantos.model;

import java.io.Serializable;

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

@Entity
@Table(name = "endereco")
@SequenceGenerator(name = "seq_endereco", sequenceName = "seq_endereco", initialValue = 1, allocationSize = 1)
public class Endereco implements Serializable {

	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "seq_endereco")
	private Long id;
	
	private String cep;
	
	private String bairro;
	
	private String rua;
	
	private String numero;
	
	private String complemento;
	
	private String uf;
	
	private String cidade;
	
	@ManyToOne(targetEntity = Pessoa.class)
	@JoinColumn(name = "pessoa_id", nullable = false, foreignKey = @ForeignKey(value = ConstraintMode.CONSTRAINT, name = "pessoa_fk"))
	private Pessoa pessoa;
	
	
	

}
