package br.com.estefanosantos.service;

import java.util.List;

import br.com.estefanosantos.exceptions.CustomException;
import br.com.estefanosantos.model.NotaFiscalCompra;

public interface NotaFiscalCompraService {
	
	public void salvarNotaFiscal(NotaFiscalCompra notaFiscalCompra) throws CustomException;
	
	public void apagarNotaFiscal(Long id) throws CustomException;
	
	public List<NotaFiscalCompra> buscarPorDesc(String desc) throws CustomException;
	
	public NotaFiscalCompra buscarPorSerieNota(String serieNota) throws CustomException;
	
	public List<NotaFiscalCompra> buscarPorEmpresa(Long id) throws CustomException;
	
	public List<NotaFiscalCompra> buscarPorPessoa(Long id) throws CustomException;
	
	

}
