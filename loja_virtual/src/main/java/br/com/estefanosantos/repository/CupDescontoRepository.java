package br.com.estefanosantos.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import br.com.estefanosantos.model.CupDesconto;

@Repository
@Transactional
public interface CupDescontoRepository extends JpaRepository<CupDesconto, Long>{

	@Query("select c from CupDesconto c where c.empresa.id = ?1")
	public List<CupDesconto> findAllByEmpresa(Long idEmpresa);
}
