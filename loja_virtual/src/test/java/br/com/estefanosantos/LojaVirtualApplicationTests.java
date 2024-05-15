package br.com.estefanosantos;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import br.com.estefanosantos.controller.RoleController;
import br.com.estefanosantos.model.Role;
import br.com.estefanosantos.repository.RoleRepository;
import junit.framework.TestCase;

@SpringBootTest(classes = LojaVirtualApplication.class)
class LojaVirtualApplicationTests extends TestCase {

	@Autowired
	RoleController roleController;
	
	@Autowired
	RoleRepository roleRepository;
	
	@Test
	void testeCadastroRole() {
		
		Role role = new Role();
		
		role.setDescricao("ROLE_USER");
		
		assertEquals(true, role.getId() == null);
		
		role = roleController.salvar(role).getBody();
		
		assertEquals(true, role.getId() > 0);
		
		assertEquals("ROLE_ADMIN", role.getDescricao());
		
		Role role2 = roleRepository.findById(role.getId()).get();
		
		
		assertEquals(role.getId(), role2.getId());
	}

}
