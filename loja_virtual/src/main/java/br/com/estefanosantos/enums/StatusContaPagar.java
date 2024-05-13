package br.com.estefanosantos.enums;

import lombok.Getter;

@Getter
public enum StatusContaPagar {
	
	COBRANCA("Pagar"),
	VENCIDA("Vencida"),
	ABERTA("Aberta"),
	QUITADA("Quitada"),
	RENEGOCIADA("Renegociada");
	
	private String descricao;
	
	StatusContaPagar(String descricao) {
		this.descricao = descricao;
	}
	
	@Override
	public String toString() {
		return this.descricao;
	}

}
