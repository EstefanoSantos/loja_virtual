package br.com.estefanosantos.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import br.com.estefanosantos.model.Usuario;

@Repository
@Transactional
public interface UsuarioRepository extends JpaRepository<Usuario, Long> {
	
	@Query(value = "select u from Usuario u where u.login = ?1")
	Usuario findUserByLogin(String login);
	
	@Query(value = "select u from Usuario u where u.pessoa.id = ?1 or u.login = ?2")
	Usuario findUserByPessoa(Long id, String email);
	
	@Query(nativeQuery = true, value = "select constraint_name from information_schema.constraint_column_usage\r\n"
			+ "where table_name = 'usuario_role' and column_name = 'role_id' and \r\n"
			+ "constraint_name <> 'unique_role_user';")
	String checkConstraintRole();
	
	@Query(nativeQuery = true, value = "insert into usuario_role(usuario_id, role_id) values (?1, (select id from role where descricao = 'ROLE_USER'))")
	void insereRoleUser(Long id);

}
