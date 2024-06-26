package br.com.estefanosantos.dto;

import br.com.estefanosantos.model.PessoaFisica;
import jakarta.validation.Valid;

public class PessoaFisicaRequestDto {
	
	@Valid
	private PessoaFisica pessoaFisica;
	private String cnpj;
	
	public PessoaFisica getPessoaFisica() {
		return pessoaFisica;
	}
	public void setPessoaFisica(PessoaFisica pessoaFisica) {
		this.pessoaFisica = pessoaFisica;
	}
	public String getCnpj() {
		return cnpj;
	}
	public void setCnpj(String cnpj) {
		this.cnpj = cnpj;
	}
	
	

}
