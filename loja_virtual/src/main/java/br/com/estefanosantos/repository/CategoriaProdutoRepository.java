package br.com.estefanosantos.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import br.com.estefanosantos.model.CategoriaProduto;

@Repository
public interface CategoriaProdutoRepository extends JpaRepository<CategoriaProduto, Long> {
	
	@Query("select p from CategoriaProduto p where upper(p.nomeCategoria) like upper(concat('%', ?1, '%'))")
	CategoriaProduto buscarPorCategoria(String categoria);
	
	@Query("select p from CategoriaProduto p where p.id = ?1")
	CategoriaProduto buscarPorId(Long id);

}
