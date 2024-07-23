package br.com.estefanosantos.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.estefanosantos.dto.FormaPagamentoDto;
import br.com.estefanosantos.exceptions.CustomException;
import br.com.estefanosantos.model.FormaPagamento;
import br.com.estefanosantos.repository.FormaPagamentoRepository;
import br.com.estefanosantos.repository.PessoaJuridicaRepository;
import br.com.estefanosantos.service.FormaPagamentoService;

@Service
public class FormaPagamentoServiceImpl implements FormaPagamentoService {
	
	@Autowired
	private FormaPagamentoRepository formaPagamentoRepository;
	
	@Autowired
	private PessoaJuridicaRepository pessoaJuridicaRepository;

	@Override
	public void salvar(FormaPagamento formaPagamento) throws CustomException {
		
		if (!pessoaJuridicaRepository.existsById(formaPagamento.getEmpresa().getId())) {
			throw new CustomException("Não foi encontrado empresa com id fornecido: " + formaPagamento.getEmpresa().getId());
		}
		
		formaPagamentoRepository.save(formaPagamento);
		
	}

	@Override
	public List<FormaPagamentoDto> findByEmpresaId(Long idEmprsa) throws CustomException {
		
		List<FormaPagamento> list = formaPagamentoRepository.findAllByEmpresa(idEmprsa);
		
		if (list.isEmpty()) {
			throw new CustomException("Não foi encontrado formas de pagamento associado a empresa de id: " +idEmprsa);
		}
		
		List<FormaPagamentoDto> listDto = new ArrayList<FormaPagamentoDto>();
		
		for (int i =0; i < list.size(); i++) {
			
			FormaPagamentoDto dto = mapToFormaPagamentoDto(list.get(i));
			
			listDto.add(dto);
		}
		
		
		return listDto;
	}
	
	private FormaPagamentoDto mapToFormaPagamentoDto(FormaPagamento formaPagamento) {
		
		FormaPagamentoDto dto = new FormaPagamentoDto();
		
		dto.setId(formaPagamento.getId());
		dto.setIdEmpresa(formaPagamento.getEmpresa().getId());
		dto.setFormaPagamento(formaPagamento.getFormaPagamento());
		
		return dto;
	}

}
