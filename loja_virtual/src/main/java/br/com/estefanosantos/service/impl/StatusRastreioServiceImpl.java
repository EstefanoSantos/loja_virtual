package br.com.estefanosantos.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.estefanosantos.dto.StatusRastreioDto;
import br.com.estefanosantos.exceptions.CustomException;
import br.com.estefanosantos.model.StatusRastreio;
import br.com.estefanosantos.repository.StatusRastreioRepository;
import br.com.estefanosantos.service.StatusRastreioService;

@Service
public class StatusRastreioServiceImpl implements StatusRastreioService {

	@Autowired
	private StatusRastreioRepository rastreioRepository;

	@Override
	public List<StatusRastreioDto> buscarRastreioVenda(Long idVenda) throws CustomException {

		List<StatusRastreio> status = rastreioRepository.buscarRastreioVenda(idVenda);

		if (status.isEmpty()) {
			throw new CustomException("Não foi encontrado status de rastreio com o id de venda fornecido: " + idVenda);
		}

		List<StatusRastreioDto> statusDto = new ArrayList<StatusRastreioDto>();

		for (StatusRastreio rastreio : status) {

			StatusRastreioDto dto = new StatusRastreioDto();

			dto.setId(rastreio.getId());
			dto.setCentroDistribuicao(rastreio.getCentroDistribuicao());
			dto.setCidade(rastreio.getCidade());
			dto.setEstado(rastreio.getEstado());
			dto.setIdEmpresa(rastreio.getEmpresa().getId());
			dto.setIdVendaCompraLoja(rastreio.getVendaCompraLoja().getId());
			dto.setStatus(rastreio.getStatus());
			
			statusDto.add(dto);
			
		}

		return statusDto;

	}

}
