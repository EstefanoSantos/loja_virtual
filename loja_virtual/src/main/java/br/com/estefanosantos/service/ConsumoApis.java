package br.com.estefanosantos.service;

import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import br.com.estefanosantos.dto.CepDto;
import br.com.estefanosantos.dto.CnpjDto;

@Service
public class ConsumoApis {
	
	public CepDto consultaCep(String cep) {
		return new RestTemplate().getForEntity("https://viacep.com.br/ws/"+ cep +"/json/", CepDto.class).getBody();
	}
	
	public CnpjDto consultaCnpj(String cnpj) {
		return new RestTemplate().getForEntity("https://receitaws.com.br/v1/cnpj/"+ cnpj, CnpjDto.class).getBody(); 
	}

}
