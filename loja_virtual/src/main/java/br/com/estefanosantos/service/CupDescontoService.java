package br.com.estefanosantos.service;

import java.util.List;

import br.com.estefanosantos.dto.CupDescontoDto;
import br.com.estefanosantos.exceptions.CustomException;

public interface CupDescontoService {
	
	
	List<CupDescontoDto> findAllByEmpresa(Long idEmpresa) throws CustomException;

}
