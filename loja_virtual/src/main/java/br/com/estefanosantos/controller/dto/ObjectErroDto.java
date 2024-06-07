package br.com.estefanosantos.controller.dto;

import java.io.Serializable;

public class ObjectErroDto implements Serializable{

	private static final long serialVersionUID = 1L;

	private String erro;
	
	private String status;

	public String getErro() {
		return erro;
	}

	public void setErro(String erro) {
		this.erro = erro;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}
	
	
}
