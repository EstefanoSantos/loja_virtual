package br.com.estefanosantos.service;

import java.sql.SQLException;

import br.com.estefanosantos.dto.VendaCompraLojaDto;
import br.com.estefanosantos.exceptions.CustomException;
import br.com.estefanosantos.model.VendaCompraLoja;

public interface VendaCompraLojaService {
	
	VendaCompraLojaDto salvarVendaCompraLoja(VendaCompraLoja vendaCompraLoja) throws CustomException;
	
	VendaCompraLojaDto buscarVendaPorId(Long id) throws CustomException;
	
	void excluirVendaTotal(Long id) throws CustomException, SQLException;

}
