package br.com.estefanosantos.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.estefanosantos.model.Endereco;

public interface EnderecoRepository extends JpaRepository<Endereco, Long>{

}
