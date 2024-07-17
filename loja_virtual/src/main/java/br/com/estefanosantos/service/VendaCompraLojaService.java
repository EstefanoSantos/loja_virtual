package br.com.estefanosantos.service;

import java.sql.SQLException;
import java.util.Date;
import java.util.List;

import br.com.estefanosantos.dto.VendaCompraLojaDto;
import br.com.estefanosantos.exceptions.CustomException;
import br.com.estefanosantos.model.VendaCompraLoja;

public interface VendaCompraLojaService {
	
	VendaCompraLojaDto salvarVendaCompraLoja(VendaCompraLoja vendaCompraLoja) throws CustomException;
	
	VendaCompraLojaDto buscarVendaPorId(Long id) throws CustomException;
	
	void excluirVendaTotal(Long id) throws CustomException, SQLException;
	
	void esconderVendaTotal(Long id) throws CustomException, SQLException;
	
	List<VendaCompraLojaDto> buscarVendaDinamica(String valor, String tipoConsulta) throws CustomException;
	
	List<VendaCompraLojaDto> buscarPorPeriodoData(Date data1, Date data2) throws CustomException;

}
