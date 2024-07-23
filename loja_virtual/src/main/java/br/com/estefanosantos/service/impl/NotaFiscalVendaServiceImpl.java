package br.com.estefanosantos.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.estefanosantos.dto.NotaFiscalVendaDto;
import br.com.estefanosantos.exceptions.CustomException;
import br.com.estefanosantos.model.NotaFiscalVenda;
import br.com.estefanosantos.repository.NotaFiscalVendaRepository;
import br.com.estefanosantos.service.NotaFiscalVendaService;

@Service
public class NotaFiscalVendaServiceImpl implements NotaFiscalVendaService {
	
	@Autowired
	private NotaFiscalVendaRepository notaFiscalVendaRepository;

	@Override
	public NotaFiscalVendaDto buscarNotaPorIdVenda(Long idVenda) throws CustomException {
		
		NotaFiscalVenda notaFiscalVenda = notaFiscalVendaRepository.buscarNotaPorIdVenda(idVenda);
		
		if (notaFiscalVenda == null) {
			throw new CustomException("Não foi encontrada nota fiscal com o id de venda: " +idVenda);
		}
		
		NotaFiscalVendaDto dto = mapToNotaFiscalDto(notaFiscalVenda);
		
		return dto;
	}
	
	
	private NotaFiscalVendaDto mapToNotaFiscalDto(NotaFiscalVenda notaFiscalVenda) {
		
		NotaFiscalVendaDto dto = new NotaFiscalVendaDto();
		
		dto.setId(notaFiscalVenda.getId());
		dto.setNumero(notaFiscalVenda.getNumero());
		dto.setXml(notaFiscalVenda.getXml());
		dto.setPdf(notaFiscalVenda.getPdf());
		dto.setTipo(notaFiscalVenda.getTipo());
		dto.setIdVendaCompraLoja(notaFiscalVenda.getVendaCompraLoja().getId());
		dto.setIdEmpresa(notaFiscalVenda.getEmpresa().getId());
		
		return dto;
	}

}
