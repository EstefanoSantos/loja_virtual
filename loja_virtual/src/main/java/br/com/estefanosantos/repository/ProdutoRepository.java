package br.com.estefanosantos.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import br.com.estefanosantos.model.Produto;

@Repository
@Transactional
public interface ProdutoRepository extends JpaRepository<Produto, Long> {
	
	
	@Query(nativeQuery = true, value = "select count(1) > 0 from produto where upper(trim(nome)) = upper(trim(?1))")
	public boolean existeProduto(String nome);
	
	@Query("select p from Produto p where upper(trim(p.nome)) like %?1%")
	public List<Produto> buscarProdutoNome(String nome);
	
	@Query("select p from Produto p where upper(trim(p.nome)) like %?1% and p.empresa.id = ?2")
	public List<Produto> buscarProdutoNome(String nome, Long idEmpresa);
	
	@Query("select p from Produto p where p.id = ?1")
	public boolean checkById(Long id);

}
