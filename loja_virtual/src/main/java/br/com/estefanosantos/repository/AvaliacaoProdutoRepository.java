package br.com.estefanosantos.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import br.com.estefanosantos.model.AvaliacaoProduto;

@Transactional
@Repository
public interface AvaliacaoProdutoRepository extends JpaRepository<AvaliacaoProduto, Long> {
	
	@Query("select a from AvaliacaoProduto a where a.produto.id = ?1")
	List<AvaliacaoProduto> buscarAvaliacaoPorProduto(Long idProduto);
	
	@Query("select a from AvaliacaoProduto a where a.produto.id = ?1 and a.pessoa.id = ?2")
	List<AvaliacaoProduto> buscarAvaliacaoPorProdutoPessoa(Long idProduto, Long idPessoa);
	
	@Query("select a from AvaliacaoProduto a where a.pessoa.id = ?1")
	List<AvaliacaoProduto> buscarAvaliacaoPorPessoa(Long idPessoa);
	
	@Modifying(flushAutomatically = true)
	@Transactional
	@Query(nativeQuery = true, value = "delete from avaliacao_produto where id = ?1 and pessoa_id = ?2")
	void apagarAvaliacaoPessoa(Long idAvaliacao, Long idPessoa);
	
	

}
