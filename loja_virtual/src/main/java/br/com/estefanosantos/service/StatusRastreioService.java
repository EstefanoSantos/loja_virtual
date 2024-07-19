package br.com.estefanosantos.service;

import java.util.List;

import br.com.estefanosantos.dto.StatusRastreioDto;
import br.com.estefanosantos.exceptions.CustomException;

public interface StatusRastreioService {
	
	public List<StatusRastreioDto> buscarRastreioVenda(Long idVenda) throws CustomException;
}
