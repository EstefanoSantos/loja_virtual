package br.com.estefanosantos.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import br.com.estefanosantos.model.ImagemProduto;

@Transactional
@Repository
public interface ImagemProdutoRepository extends JpaRepository<ImagemProduto, Long> {
	
	@Query("select i from ImagemProduto i where i.produto.id = ?1")
	List<ImagemProduto> buscarImagensProduto(Long idProduto);
	
	@Modifying(flushAutomatically = true)
	@Transactional
	@Query(nativeQuery = true, value = "delete from imagem_produto where produto_id = ?1")
	void apagarImagemPorProduto(Long idProduto);

}
