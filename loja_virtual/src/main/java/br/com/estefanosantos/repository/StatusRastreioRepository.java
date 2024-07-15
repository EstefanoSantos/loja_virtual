package br.com.estefanosantos.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import br.com.estefanosantos.model.StatusRastreio;

@Transactional
@Repository
public interface StatusRastreioRepository extends JpaRepository<StatusRastreio, Long>{

}
