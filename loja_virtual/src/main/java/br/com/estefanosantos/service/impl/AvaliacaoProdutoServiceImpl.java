package br.com.estefanosantos.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.estefanosantos.dto.AvaliacaoProdutoDto;
import br.com.estefanosantos.exceptions.CustomException;
import br.com.estefanosantos.model.AvaliacaoProduto;
import br.com.estefanosantos.repository.AvaliacaoProdutoRepository;
import br.com.estefanosantos.repository.PessoaFisicaRepository;
import br.com.estefanosantos.repository.PessoaJuridicaRepository;
import br.com.estefanosantos.repository.ProdutoRepository;
import br.com.estefanosantos.service.AvaliacaoProdutoService;

@Service
public class AvaliacaoProdutoServiceImpl implements AvaliacaoProdutoService {

	@Autowired
	private AvaliacaoProdutoRepository avaliacaoProdutoRepository;

	@Autowired
	private PessoaFisicaRepository fisicaRepository;

	@Autowired
	private ProdutoRepository produtoRepository;

	@Autowired
	private PessoaJuridicaRepository juridicaRepository;

	@Override
	public void salvarAvaliacaoProduto(AvaliacaoProduto avaliacaoProduto) throws CustomException {

		if (!fisicaRepository.existsById(avaliacaoProduto.getPessoa().getId())) {
			throw new CustomException("Não encontramos pesssoa com o id informado");
		}

		if (!produtoRepository.existsById(avaliacaoProduto.getProduto().getId())) {
			throw new CustomException("Não encontramos produto com o id informado");
		}

		if (!juridicaRepository.existsById(avaliacaoProduto.getEmpresa().getId())) {
			throw new CustomException("Não encontramos empresa com o id informado");
		}

		avaliacaoProdutoRepository.save(avaliacaoProduto);

	}

	@Override
	public List<AvaliacaoProdutoDto> buscarAvaliacaoPorProduto(Long idProduto) throws CustomException {

		List<AvaliacaoProduto> avaliacoes = avaliacaoProdutoRepository.buscarAvaliacaoPorProduto(idProduto);

		if (avaliacoes.isEmpty()) {
			throw new CustomException(
					"Não foi encontrada avaliação de produto com o id do produto informado: " + idProduto);
		}

		List<AvaliacaoProdutoDto> dtos = new ArrayList<AvaliacaoProdutoDto>();

		for (AvaliacaoProduto avaliacao : avaliacoes) {

			AvaliacaoProdutoDto dto = new AvaliacaoProdutoDto();

			dto.setId(avaliacao.getId());
			dto.setDescricao(avaliacao.getDescricao());
			dto.setNota(avaliacao.getNota());
			dto.setPessoaId(avaliacao.getPessoa().getId());
			dto.setProdutoId(avaliacao.getProduto().getId());
			dto.setEmpresaId(avaliacao.getEmpresa().getId());

			dtos.add(dto);
		}

		return dtos;
	}

	@Override
	public List<AvaliacaoProdutoDto> buscarAvaliacaoPorProdutoPessoa(Long idProduto, Long idPessoa)
			throws CustomException {

		List<AvaliacaoProduto> avaliacoes = avaliacaoProdutoRepository.buscarAvaliacaoPorProdutoPessoa(idProduto,
				idPessoa);

		if (avaliacoes.isEmpty()) {
			throw new CustomException("Não foi encontrado avaliação com o id de produto e pessoa informado.");
		}

		List<AvaliacaoProdutoDto> dtos = new ArrayList<AvaliacaoProdutoDto>();

		for (AvaliacaoProduto avaliacao : avaliacoes) {

			AvaliacaoProdutoDto dto = new AvaliacaoProdutoDto();

			dto.setId(avaliacao.getId());
			dto.setDescricao(avaliacao.getDescricao());
			dto.setNota(avaliacao.getNota());
			dto.setPessoaId(avaliacao.getPessoa().getId());
			dto.setProdutoId(avaliacao.getProduto().getId());
			dto.setEmpresaId(avaliacao.getEmpresa().getId());

			dtos.add(dto);
		}

		return dtos;
	}

	@Override
	public List<AvaliacaoProdutoDto> buscarAvaliacaoPorPessoa(Long idPessoa) throws CustomException {

		List<AvaliacaoProduto> avaliacoes = avaliacaoProdutoRepository.buscarAvaliacaoPorPessoa(idPessoa);

		if (avaliacoes.isEmpty()) {
			throw new CustomException("Não foi encontrada avaliação com o id de pessoa informado.");
		}

		List<AvaliacaoProdutoDto> dtos = new ArrayList<AvaliacaoProdutoDto>();

		for (AvaliacaoProduto avaliacao : avaliacoes) {

			AvaliacaoProdutoDto dto = new AvaliacaoProdutoDto();

			dto.setId(avaliacao.getId());
			dto.setDescricao(avaliacao.getDescricao());
			dto.setNota(avaliacao.getNota());
			dto.setPessoaId(avaliacao.getPessoa().getId());
			dto.setProdutoId(avaliacao.getProduto().getId());
			dto.setEmpresaId(avaliacao.getEmpresa().getId());

			dtos.add(dto);
		}

		return dtos;
	}

	@Override
	public void apagarAvaliacaoPessoa(Long idAvaliacao, Long idPessoa) throws CustomException {
		
		if (!avaliacaoProdutoRepository.existsById(idAvaliacao)) {
			throw new CustomException("Não foi encontrada avaliação com id informado.");
		}
		
		if (!fisicaRepository.existsById(idPessoa)) {
			throw new CustomException("Não foi encontrada pessoa com id informado.");
		}
		
		avaliacaoProdutoRepository.apagarAvaliacaoPessoa(idAvaliacao, idPessoa);
		
	}

}
