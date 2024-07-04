package br.com.estefanosantos.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import br.com.estefanosantos.model.ContaPagar;

@Repository
@Transactional
public interface ContaPagarRepository extends JpaRepository<ContaPagar, Long> {
	
	@Query(value = "select c from ContaPagar c where c.id = ?1")
	ContaPagar buscarPorId(Long id);
	
	@Query(value = "select c from ContaPagar c where upper(trim(c.descricao)) like %?1%")
	List<ContaPagar> buscarContasPorDesc(String desc);
}
