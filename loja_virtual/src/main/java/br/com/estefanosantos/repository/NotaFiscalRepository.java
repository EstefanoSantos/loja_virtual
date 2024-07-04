package br.com.estefanosantos.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import br.com.estefanosantos.model.NotaFiscalCompra;

@Repository
@Transactional
public interface NotaFiscalRepository extends JpaRepository<NotaFiscalCompra, Long>{
	
	@Query(value = "select n from NotaFiscalCompra n where n.pessoa.id = ?1")
	List<NotaFiscalCompra> buscarNotaPorPessoa(Long id);
	
	@Query(value = "select n from NotaFiscalCompra n where n.empresa.id = ?1")
	List<NotaFiscalCompra> buscarNotaPorEmpresa(Long id);
	
	@Query(value = "select n from NotaFiscalCompra n where n.numeroNota = ?1")
	NotaFiscalCompra buscarNotaPorNumero(String numeroNota);
	
	@Query(value = "select n from NotaFiscalCompra n where n.serieNota = ?1")
	NotaFiscalCompra buscarNotaPorSerie(String serieNota);
	
	@Query(value = "select n from NotaFiscalCompra n where upper(trim(n.descricaoObs)) like %?1%")
	List<NotaFiscalCompra> buscarNotasPorDesc(String desc);
	
	@Query(value = "select n from NotaFiscalCompra n where n.contaPagar.id = ?1")
	NotaFiscalCompra buscarNotaContaPagar(Long id);
	
	@Query(value = "select n from NotaFiscalCompra n where n.id = ?1")
	NotaFiscalCompra buscarNotaId(Long id);
	
	@Modifying(flushAutomatically = true, clearAutomatically = true)
	@Transactional
	@Query(nativeQuery = true, value = "delete from nota_item_produto where nota_fiscal_compra_id = ?1")
	void apagarItemProduto(Long id);
	
}
