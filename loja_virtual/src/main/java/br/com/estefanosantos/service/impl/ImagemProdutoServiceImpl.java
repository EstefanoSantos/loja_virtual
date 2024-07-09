package br.com.estefanosantos.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.estefanosantos.dto.ImagemProdutoDto;
import br.com.estefanosantos.exceptions.CustomException;
import br.com.estefanosantos.model.ImagemProduto;
import br.com.estefanosantos.repository.ImagemProdutoRepository;
import br.com.estefanosantos.repository.PessoaJuridicaRepository;
import br.com.estefanosantos.repository.ProdutoRepository;
import br.com.estefanosantos.service.ImagemProdutoService;

@Service
public class ImagemProdutoServiceImpl implements ImagemProdutoService {
	
	
	@Autowired
	private ImagemProdutoRepository imagemProdutoRepository;
	
	@Autowired
	private PessoaJuridicaRepository juridicaRepository;
	
	@Autowired
	private ProdutoRepository produtoRepository;

	@Override
	public List<ImagemProdutoDto> buscarImagensProduto(Long idProduto) throws CustomException {
		
		List<ImagemProduto> imagensProduto = imagemProdutoRepository.buscarImagensProduto(idProduto);
		
		if (imagensProduto.isEmpty()) {
			throw new CustomException("Não foi encontrado produto com esse id.");
		}
		
		List<ImagemProdutoDto> dtos = new ArrayList<ImagemProdutoDto>();
		
		for (ImagemProduto imagem : imagensProduto) {
			ImagemProdutoDto dto = new ImagemProdutoDto();
			
			dto.setId(imagem.getId());
			dto.setImagemOriginal(imagem.getImagemOriginal());
			dto.setImagemMiniatura(imagem.getImagemMiniatura());
			dto.setProdutoId(imagem.getProduto().getId());
			dto.setEmpresaId(imagem.getEmpresa().getId());
			
			dtos.add(dto);
		}
		
		return dtos;
	}

	@Override
	public void apagarImagensPorProduto(Long idProduto) throws CustomException {
		
		List<ImagemProduto> imagensProduto = imagemProdutoRepository.buscarImagensProduto(idProduto);
		
		if (imagensProduto.isEmpty()) {
			throw new CustomException("Não foi encontrado produto com esse id.");
		}
		
		imagemProdutoRepository.apagarImagemPorProduto(idProduto);
	}

	@Override
	public void apagarImagemProdutoPorId(Long id) throws CustomException {
		
		if (imagemProdutoRepository.existsById(id)) {
			
			imagemProdutoRepository.deleteById(id);
			
		} else {
			throw new CustomException("Não foi encontrado produto com o id informado: " + id);
		}
		
	}

	@Override
	public ImagemProdutoDto salvarImagemProduto(ImagemProduto imagemProduto) throws CustomException {
		
		if (!produtoRepository.existsById(imagemProduto.getProduto().getId())) {
			throw new CustomException("Não foi encontrado produto com o id informado: " +imagemProduto.getProduto().getId());
		}
		
		if (!juridicaRepository.existsById(imagemProduto.getEmpresa().getId())) {
			throw new CustomException("Não foi encontrada empresa com o id informado: " +imagemProduto.getEmpresa().getId());
		}
		
		ImagemProduto imagem = imagemProdutoRepository.save(imagemProduto);
		
		ImagemProdutoDto dto = new ImagemProdutoDto();
		
		dto.setId(imagem.getId());
		dto.setImagemOriginal(imagem.getImagemOriginal());
		dto.setImagemMiniatura(imagem.getImagemMiniatura());
		dto.setProdutoId(imagem.getProduto().getId());
		dto.setEmpresaId(imagem.getEmpresa().getId());
		
		return dto;
	}
	
	

}
