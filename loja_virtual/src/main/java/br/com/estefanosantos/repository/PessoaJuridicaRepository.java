package br.com.estefanosantos.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import br.com.estefanosantos.model.PessoaJuridica;

@Repository
public interface PessoaJuridicaRepository extends JpaRepository<PessoaJuridica, Long> {
	
	@Query(value = "select pj from PessoaJuridica pj where pj.cnpj = ?1")
	PessoaJuridica existeCnpj(String cnpj);
	
	@Query(value = "select pj from PessoaJuridica pj where pj.inscricaoEstadual = ?1")
	PessoaJuridica existeInscricaoEstadual(String inscricaoEstadual);
	
	@Query(value = "select pj from PessoaJuridica pj where pj.email = ?1")
	PessoaJuridica existeEmail(String email);
	
	
}
