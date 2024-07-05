package br.com.estefanosantos.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import br.com.estefanosantos.model.NotaItemProduto;

@Repository
@Transactional
public interface NotaItemProdutoRepository extends JpaRepository<NotaItemProduto, Long> {
	
	@Query(nativeQuery = true, value = "select * from nota_item_produto where produto_id = ?1 and nota_fiscal_compra_id = ?2")
	NotaItemProduto buscarNotaItemPorProdutoNota(Long idProduto, Long idNota);
	
	@Query("select n from NotaItemProduto n where n.produto.id = ?1")
	List<NotaItemProduto> buscarPorProduto(Long id);
	
	@Query("select n from NotaItemProduto n where n.empresa.id = ?1")
	List<NotaItemProduto> buscarPorEmpresa(Long id);
	
	@Query("select n from NotaItemProduto n where n.notaFiscalCompra.id = ?1")
	List<NotaItemProduto> buscarPorNotaFiscal(Long id);
}
