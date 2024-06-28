package br.com.estefanosantos.dto;

import java.io.Serializable;

public class CategoriaProdutoDto implements Serializable {

	private static final long serialVersionUID = 1L;
	
	private Long id;
	private String nomeCategoria;
	private String empresa;
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getNomeCategoria() {
		return nomeCategoria;
	}
	public void setNomeCategoria(String nomeCategoria) {
		this.nomeCategoria = nomeCategoria;
	}
	public String getEmpresa() {
		return empresa;
	}
	public void setEmpresa(String empresa) {
		this.empresa = empresa;
	}
	
	

}
