package br.com.estefanosantos.enums;

import lombok.Getter;
import lombok.ToString;

@ToString
@Getter
public enum TipoEndereco {
	
	COBRANCA("Cobrança"),
	ENTREGA("Entrega");
	
	private String descricao;
	
	TipoEndereco(String descricao) {
		this.descricao = descricao;
	}
}
