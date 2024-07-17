package br.com.estefanosantos.repository;

import java.util.Date;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import br.com.estefanosantos.model.VendaCompraLoja;

@Transactional
@Repository
public interface VendaCompraLojaRepository extends JpaRepository<VendaCompraLoja, Long> {
	
	@Query("select v from VendaCompraLoja v where v.pessoa.id = ?1")
	List<VendaCompraLoja> buscarPorPessoa(Long idPessoa);
	
	@Query("select v from VendaCompraLoja v where v.formaPagamento.id = ?1")
	List<VendaCompraLoja> buscarPorFormaDePagamento(Long idFormaPagamento);
	
	@Query("select v from VendaCompraLoja v where v.empresa.id = ?1")
	List<VendaCompraLoja> buscarPorEmpresa(Long idEmpresa);
	
	@Query("select v.vendaCompraLoja from ItemVenda v where v.vendaCompraLoja.excluido = false and v.produto.id = ?1")
	List<VendaCompraLoja> buscarPorProduto(Long id);
	
	@Query("select v.vendaCompraLoja from ItemVenda v where v.vendaCompraLoja.excluido = false and upper(trim(v.produto.nome)) like %?1%")
	List<VendaCompraLoja> buscarPorNomeProduto(String nome);
	
	@Query("select v.vendaCompraLoja from ItemVenda v where v.vendaCompraLoja.excluido = false and upper(trim(v.vendaCompraLoja.pessoa.nome)) like %?1%")
	List<VendaCompraLoja> buscarPorNomeCliente(String nome);	
	
	@Query("select v.vendaCompraLoja from ItemVenda v where v.vendaCompraLoja.excluido = false"
			+ " and v.vendaCompraLoja.dataVenda between ?1 and ?2")
	List<VendaCompraLoja> buscarPorPeriodoData(Date data1, Date data2);
	
	@Query("select v.vendaCompraLoja from ItemVenda v where v.vendaCompraLoja.excluido = false and v.vendaCompraLoja.pessoa.cpf = ?1")
	List<VendaCompraLoja> buscarPorCpf(String cpf);
}
