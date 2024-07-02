package br.com.estefanosantos.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import br.com.estefanosantos.model.MarcaProduto;

public interface MarcaProdutoRepository extends JpaRepository<MarcaProduto, Long> {
	
	@Query("select m from MarcaProduto m where upper(m.marcaNome) like %?1%")
	List<MarcaProduto> buscarMarcaNome(String nome);
	
	@Query("select m from MarcaProduto m where upper(m.marcaNome) like %?1% and m.empresa.id = ?2")
	List<MarcaProduto> buscarMarcaNome(String nome, Long empresaId);
}
