package br.com.estefanosantos.repository;

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

}
