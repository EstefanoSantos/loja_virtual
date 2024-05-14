package br.com.estefanosantos;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import br.com.estefanosantos.controller.RoleController;
import br.com.estefanosantos.model.Role;

@SpringBootTest(classes = LojaVirtualApplication.class)
class LojaVirtualApplicationTests {

	@Autowired
	RoleController roleController;
	
	@Test
	void testeCadastroAcesso() {
		
		Role role = new Role();
		
		role.setDescricao("ROLE_USER");
		
		roleController.salvar(role);
	}

}
