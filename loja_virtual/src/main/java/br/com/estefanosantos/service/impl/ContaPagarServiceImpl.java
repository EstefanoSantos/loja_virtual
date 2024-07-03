package br.com.estefanosantos.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.estefanosantos.exceptions.CustomException;
import br.com.estefanosantos.model.ContaPagar;
import br.com.estefanosantos.repository.ContaPagarRepository;
import br.com.estefanosantos.service.ContaPagarService;

@Service
public class ContaPagarServiceImpl implements ContaPagarService {

	@Autowired
	private ContaPagarRepository contaPagarRepository;

	@Override
	public void salvarContaPagar(ContaPagar contaPagar) throws CustomException {
		
		 ContaPagar existeConta = contaPagarRepository.buscarPorId(contaPagar.getId());
		 
		 if (existeConta != null) {
			 throw new CustomException("Já existe conta cadastrada com esse id.");
		 }
		 
		 contaPagarRepository.save(contaPagar);

	}
	@Override
	public void apagarContaPagar(Long id) throws CustomException {
		
		ContaPagar existeConta = contaPagarRepository.buscarPorId(id);
		
		if (existeConta == null) {
			throw new CustomException("Não foi encontrada conta a pagar com esse id.");
		}
		
		contaPagarRepository.deleteById(id);

	}

	@Override
	public ContaPagar selecionarContaPagar(Long id) throws CustomException {
		
		ContaPagar conta = contaPagarRepository.buscarPorId(id);
		
		if (conta == null) {
			throw new CustomException("Não foi encontrada conta a pagar com esse id.");
		}
		
		return conta;
	}

	@Override
	public List<ContaPagar> selecionarContasPagar() throws CustomException {
		
		List<ContaPagar> contas = contaPagarRepository.findAll();
		
		if (contas.isEmpty()) {
			throw new CustomException("Ainda não existe contas a pagar cadastradas.");
		}
		
		return contas;
	}
	@Override
	public List<ContaPagar> selecionarContaPagarDesc(String desc) throws CustomException {
		
		List<ContaPagar> contas = contaPagarRepository.buscarContasPorDesc(desc);
		
		if (contas.isEmpty()) {
			throw new CustomException("Não foram encontradas contas com essa descrição.");
		}
		
		return contas;
	}

}
