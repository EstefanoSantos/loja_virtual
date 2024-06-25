package br.com.estefanosantos.service;

import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import br.com.estefanosantos.dto.CepDto;

@Service
public class ConsumoApis {
	
	public CepDto consultaCep(String cep) {
		return new RestTemplate().getForEntity("https://viacep.com.br/ws/"+ cep +"/json/", CepDto.class).getBody();
	}

}
