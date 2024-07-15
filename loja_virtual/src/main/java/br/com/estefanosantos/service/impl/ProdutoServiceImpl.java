package br.com.estefanosantos.service.impl;

import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.List;
import java.util.stream.Collectors;

import javax.imageio.ImageIO;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import br.com.estefanosantos.exceptions.CustomException;
import br.com.estefanosantos.model.Produto;
import br.com.estefanosantos.repository.ProdutoRepository;
import br.com.estefanosantos.service.ProdutoService;
import jakarta.mail.MessagingException;
import jakarta.xml.bind.DatatypeConverter;

@Service
public class ProdutoServiceImpl implements ProdutoService {
 
	@Autowired
	ProdutoRepository produtoRepository;
	
	@Autowired
	EmailService emailService;

	@Override
	public void salvarProduto(Produto produto) throws CustomException, IOException {
		
		List<Produto> produtos = produtoRepository.buscarProdutoNome(produto.getNome().toUpperCase(), produto.getEmpresa().getId());
		
		if (!produtos.isEmpty()) {
			throw new CustomException("Produto já cadastrado.");
		}
		
		for (int i = 0; i < produto.getImagens().size(); i++) {
			produto.getImagens().get(i).setProduto(produto);
			produto.getImagens().get(i).setEmpresa(produto.getEmpresa());
			
			String base64 = "";
			
			if (produto.getImagens().get(i).getImagemOriginal().contains("data:image")) {
				base64 = produto.getImagens().get(i).getImagemOriginal().split(",")[1];
			} else {
				base64 = produto.getImagens().get(i).getImagemOriginal();
			}
			
			byte[] imageBytes = DatatypeConverter.parseBase64Binary(base64);
			
			BufferedImage bufferedImage = ImageIO.read(new ByteArrayInputStream(imageBytes));
			
			if (bufferedImage != null) {
				
				int type = bufferedImage.getType() == 0 ? BufferedImage.TYPE_INT_ARGB : bufferedImage.getType();
				int largura = Integer.parseInt("800");
				int altura = Integer.parseInt("600");
				
				BufferedImage resizedImage = new BufferedImage(largura, altura, type);
				
				Graphics2D g = resizedImage.createGraphics();
				g.drawImage(bufferedImage, 0, 0, largura, altura, null);
				g.dispose();
				
				ByteArrayOutputStream baos = new ByteArrayOutputStream();
				ImageIO.write(resizedImage, "png", baos);
				
				String miniBase64Image = "data:image/png;base64," + DatatypeConverter.printBase64Binary(baos.toByteArray());
				
				produto.getImagens().get(i).setImagemMiniatura(miniBase64Image);
				
				bufferedImage.flush();
				resizedImage.flush();
				baos.flush();
				baos.close();
						
			}
		}
			
		produtoRepository.save(produto);
	}
	
	@Override
	public void deleteProduto(Long id) throws CustomException {
		
		if (produtoRepository.checkById(id) == false) {
			throw new CustomException("Não existe produto cadastrado com esse id.");
		}
		
		produtoRepository.deleteById(id);
	}

	@Override
	public List<Produto> encontarPorNome(String nome) throws CustomException {
		
		List<Produto> produtos = produtoRepository.buscarProdutoNome(nome.toUpperCase());
		
		if (produtos == null) {
			throw new CustomException("Não foi encontrado produtos com esse nome");
		}

		return produtos;
		
	}

	//@Scheduled(initialDelay = 2000, fixedDelay = 86400000) //para teste
	@Scheduled(cron = "0 0 9 * * *")
	@Override
	public void verificarEstoqueProdutos() throws UnsupportedEncodingException, MessagingException {
		
		List<Produto> produtos = produtoRepository.findAll();
		
		if (!produtos.isEmpty()) {			
			
		List<Produto> produtosFiltrados = produtos.stream()
			.filter(produto -> produto.getDesejaAlerta())
			.filter(produto -> produto.getQuantidadeEstoque() < 30)
			.collect(Collectors.toList());
			
		StringBuilder html = new StringBuilder();	
		
		for (Produto produto : produtosFiltrados) {
			if (produto.getEmpresa().getEmail() != null) {
							
				html.append("<h2>Produto com estoque baixo!</h2>");
				html.append("<p>Produto: ").append(produto.getNome()).append("</p>");
				html.append("<p>Quantidade em estoque: " +produto.getQuantidadeEstoque()).append("</p>");
				
				emailService.sendEmailHtml(produto.getEmpresa().getEmail(), "Estoque baixo de produto", html.toString());
								
				html.setLength(0);
			}
		}
		}
	}	
}
