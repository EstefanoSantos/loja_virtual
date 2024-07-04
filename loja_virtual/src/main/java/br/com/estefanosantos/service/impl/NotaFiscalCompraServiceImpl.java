package br.com.estefanosantos.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.estefanosantos.exceptions.CustomException;
import br.com.estefanosantos.model.NotaFiscalCompra;
import br.com.estefanosantos.repository.NotaFiscalRepository;
import br.com.estefanosantos.service.NotaFiscalCompraService;

@Service
public class NotaFiscalCompraServiceImpl implements NotaFiscalCompraService {
	
	@Autowired
	private NotaFiscalRepository notaFiscalRepository;

	@Override
	public void salvarNotaFiscal(NotaFiscalCompra notaFiscalCompra) throws CustomException {
		
		NotaFiscalCompra nota =  notaFiscalRepository.buscarNotaPorNumero(notaFiscalCompra.getNumeroNota());
		
		if (nota != null) {
			throw new CustomException("Já existe nota fiscal cadastrada com esse numero.");
		}
		
		notaFiscalRepository.save(notaFiscalCompra);
	}

	@Override
	public void apagarNotaFiscal(Long id) throws CustomException {
		
		NotaFiscalCompra nota = notaFiscalRepository.buscarNotaId(id);
		
		if (nota != null) {
			notaFiscalRepository.apagarItemProduto(id); //apagar os registros na tabela filha que contenham esse id
			notaFiscalRepository.deleteById(id); //apagar os registros na tabela pai
		} else {
			throw new CustomException("Não foi encontrada nota com esse id");
		}
		
	}

	@Override
	public List<NotaFiscalCompra> buscarPorDesc(String desc) throws CustomException {
		
		List<NotaFiscalCompra> notas = notaFiscalRepository.buscarNotasPorDesc(desc);
		
		if (notas.isEmpty()) {
			throw new CustomException("Não foram encontradas notas com essa descrição");
		}
		
		return notas;
	}

	@Override
	public NotaFiscalCompra buscarPorSerieNota(String serieNota) throws CustomException {
		
		NotaFiscalCompra nota = notaFiscalRepository.buscarNotaPorSerie(serieNota);
		
		if (nota == null) {
			throw new CustomException("Não foi encontrada nota com essa série.");
		}
		
		return nota;
	}

	@Override
	public List<NotaFiscalCompra> buscarPorEmpresa(Long id) throws CustomException {
		
		List<NotaFiscalCompra> notas = notaFiscalRepository.buscarNotaPorEmpresa(id);
		
		if (notas.isEmpty()) {
			throw new CustomException("Id fornecido está errado ou empresa não possui nota fiscal.");
		}
		
		return notas;
	}

	@Override
	public List<NotaFiscalCompra> buscarPorPessoa(Long id) throws CustomException {
		
		List<NotaFiscalCompra> notas = notaFiscalRepository.buscarNotaPorPessoa(id);
		
		if (notas.isEmpty()) {
			throw new CustomException("Id errado ou pessoa não possui notas cadastradas.");
		}
		
		return notas;
	}

}
