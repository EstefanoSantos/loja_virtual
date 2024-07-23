package br.com.estefanosantos.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.estefanosantos.dto.CupDescontoDto;
import br.com.estefanosantos.exceptions.CustomException;
import br.com.estefanosantos.model.CupDesconto;
import br.com.estefanosantos.repository.CupDescontoRepository;
import br.com.estefanosantos.service.CupDescontoService;

@Service
public class CupDescontoServiceImpl implements CupDescontoService{
	
	@Autowired
	private CupDescontoRepository cupDescontoRepository;

	@Override
	public List<CupDescontoDto> findAllByEmpresa(Long idEmpresa) throws CustomException {
	
		List<CupDesconto> list = cupDescontoRepository.findAllByEmpresa(idEmpresa);
		
		if (list.isEmpty()) {
			throw new CustomException("Não foi encontrado cupom de desconto para a empresa de id: " +idEmpresa); 
		}
		
		List<CupDescontoDto> listDto = new ArrayList<CupDescontoDto>();
		
		for (int i =0; i < list.size(); i++) {
			
			CupDescontoDto dto = mapToCupDescontoDto(list.get(i));
			
			listDto.add(dto);
		}
		
		return listDto;
	}
	
	private CupDescontoDto mapToCupDescontoDto(CupDesconto cupDesconto) {
		
		CupDescontoDto dto = new CupDescontoDto();
		dto.setId(cupDesconto.getId());
		dto.setCodigoDescricao(cupDesconto.getCodigoDescricao());
		dto.setValorRealDes(cupDesconto.getValorRealDesc());
		dto.setValorPorcentagemDesc(cupDesconto.getValorPorcentagemDesc());
		dto.setIdEmpresa(cupDesconto.getEmpresa().getId());
		
		return dto;
	}

}
