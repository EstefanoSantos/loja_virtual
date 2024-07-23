package br.com.estefanosantos.service;

import br.com.estefanosantos.dto.NotaFiscalVendaDto;
import br.com.estefanosantos.exceptions.CustomException;

public interface NotaFiscalVendaService {
	
	NotaFiscalVendaDto buscarNotaPorIdVenda(Long idVenda) throws CustomException;

}
