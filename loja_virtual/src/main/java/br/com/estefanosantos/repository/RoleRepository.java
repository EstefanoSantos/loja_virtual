package br.com.estefanosantos.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import br.com.estefanosantos.model.Role;

@Repository
@Transactional
public interface RoleRepository extends JpaRepository<Role, Long> {
	
	@Query("select a from Role a where upper(trim(a.descricao)) like %?1%")
	List<Role> buscarPorDesc(String desc);
		
	
	
}
