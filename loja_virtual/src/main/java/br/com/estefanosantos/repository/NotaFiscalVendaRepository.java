package br.com.estefanosantos.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

import br.com.estefanosantos.model.NotaFiscalVenda;

@Transactional
public interface NotaFiscalVendaRepository  extends JpaRepository<NotaFiscalVenda, Long>{
	
	@Modifying
	@Transactional
	@Query(nativeQuery = true, value = "insert into nota_fiscal_venda (venda_compra_loja_id) values (?1)")
	void insertVendaCompraLoja(Long id);
}
