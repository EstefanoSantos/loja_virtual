package br.com.estefanosantos.repository;

import br.com.estefanosantos.model.PessoaFisica;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface PessoaFisicaRepository extends JpaRepository<PessoaFisica, Long> {
	
	@Query("select pf from PessoaFisica pf where pf.cpf = ?1")
	PessoaFisica existeCpf(String cpf);
	
	@Query("select pf from PessoaFisica pf where pf.email = ?1")
	PessoaFisica existeEmail(String email);
	
	@Query("select pf from PessoaFisica pf where upper(trim(pf.nome)) like upper(concat('%', ?1, '%'))")
	List<PessoaFisica> buscarPorNome(String nome);
	
}
	