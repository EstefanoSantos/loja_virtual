package br.com.estefanosantos.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import br.com.estefanosantos.model.NotaFiscalVenda;

@Repository
@Transactional
public interface NotaFiscalVendaRepository  extends JpaRepository<NotaFiscalVenda, Long> {
	
	@Query("select n from NotaFiscalVenda n where n.vendaCompraLoja.id = ?1")
	NotaFiscalVenda buscarNotaPorIdVenda(Long idVenda);
	
}
