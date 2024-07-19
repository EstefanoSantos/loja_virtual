package br.com.estefanosantos.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import br.com.estefanosantos.model.StatusRastreio;

@Transactional
@Repository
public interface StatusRastreioRepository extends JpaRepository<StatusRastreio, Long> {
	
	
	@Query("select s from StatusRastreio s where s.vendaCompraLoja.excluido = false and s.vendaCompraLoja.id = ?1 order by s.id")
	public List<StatusRastreio> buscarRastreioVenda(Long idVenda);

}
