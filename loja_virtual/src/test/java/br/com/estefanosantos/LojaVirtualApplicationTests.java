package br.com.estefanosantos;

import org.springframework.http.MediaType;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.DefaultMockMvcBuilder;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import br.com.estefanosantos.controller.RoleController;
import br.com.estefanosantos.model.Role;
import br.com.estefanosantos.repository.RoleRepository;
import junit.framework.TestCase;

@SpringBootTest(classes = LojaVirtualApplication.class)
class LojaVirtualApplicationTests extends TestCase {

	@Autowired
	private RoleController roleController;
	
	@Autowired
	private RoleRepository roleRepository;
	
	@Autowired
	private WebApplicationContext wac;
	
	/** Teste do endpoint para salvar autorizações (roles) de usuários **/
	@Test
	public void testeRestApiCadastroRole() throws JsonProcessingException, Exception {
		
		/**criacao objeto mock**/
		
		DefaultMockMvcBuilder builder = MockMvcBuilders.webAppContextSetup(this.wac);
		MockMvc mockMvc = builder.build();
		
		Role role = new Role();
		
		role.setDescricao("ROLE_COMPRADOR");
		
		/** criação objeto para mapeamento objeto java - json **/
		
		ObjectMapper mapper = new ObjectMapper();
		
		ResultActions retornoApi = mockMvc.perform(MockMvcRequestBuilders.post("/salvar-role")
												.content(mapper.writeValueAsString(role))
												.accept(MediaType.APPLICATION_JSON)
												.contentType(MediaType.APPLICATION_JSON));
		
		Role objetoRetorno = mapper.readValue(retornoApi.andReturn().getResponse().getContentAsString(), Role.class);
				
		
		assertEquals(role.getDescricao(), objetoRetorno.getDescricao());
		
	}
	
	@Test
	public void testeRestApiBuscarRole() throws JsonProcessingException, Exception {
		
		DefaultMockMvcBuilder builder = MockMvcBuilders.webAppContextSetup(this.wac);
		MockMvc mockMvc = builder.build();
		
		Role role = new Role();
		role.setDescricao("ROLE_GET_BY_ID");
		
		role = roleRepository.save(role);
		
		ObjectMapper mapper = new ObjectMapper();
		
		ResultActions result = mockMvc.perform(MockMvcRequestBuilders.get("/buscar-role/" + role.getId())
																	 .content(mapper.writeValueAsString(role))
																	 .accept(MediaType.APPLICATION_JSON)
																	 .contentType(MediaType.APPLICATION_JSON));
		
		Role roleRetorno = mapper.readValue(result.andReturn().getResponse().getContentAsString(), Role.class);
		
		assertEquals(role.getDescricao(), roleRetorno.getDescricao());
		assertEquals(role.getId(), roleRetorno.getId());
	}
	
	@Test
	public void testeRestApiDeletarRole() throws Exception {
		
		DefaultMockMvcBuilder builder = MockMvcBuilders.webAppContextSetup(this.wac);
		MockMvc mockMvc = builder.build();
		
		Long id = 18L;
		
		ResultActions retornoApi = mockMvc.perform(MockMvcRequestBuilders.delete("/apagar-role/" + id));
				                                  
		System.out.println("Status Api: " + retornoApi.andReturn().getResponse().getStatus());
		System.out.println("Retorno Api: " + retornoApi.andReturn().getResponse().getContentAsString());
		
		assertEquals("Registro removido!", retornoApi.andReturn().getResponse().getContentAsString());
		assertEquals(200, retornoApi.andReturn().getResponse().getStatus());
		
	}
	
	@Test
	public void testeRestApiBuscarRolePorDesc() throws JsonProcessingException, Exception {
		
		DefaultMockMvcBuilder builder = MockMvcBuilders.webAppContextSetup(this.wac);
		MockMvc mockMvc = builder.build();
		
		Role role = new Role();
		role.setDescricao("TESTE_OBTER_LIST");
		
		role = roleRepository.save(role);
		
		ObjectMapper mapper = new ObjectMapper();
		
		ResultActions result = mockMvc.perform(MockMvcRequestBuilders.get("/buscar-por-desc/OBTER_LIST")
																	 .content(mapper.writeValueAsString(role))
																	 .accept(MediaType.APPLICATION_JSON)
																	 .contentType(MediaType.APPLICATION_JSON));
		
		assertEquals(200, result.andReturn().getResponse().getStatus());
		
		List<Role> retornoApiList = mapper.readValue(result.andReturn().getResponse().getContentAsString(),
										  new TypeReference<List<Role>>() {
										});
		
		assertEquals(1, retornoApiList.size());
		assertEquals(role.getDescricao(), retornoApiList.get(0).getDescricao());
		
		roleRepository.deleteById(role.getId());
	}
	
	
	
	@Test
	void testeCadastroRole() {
		
		Role role = new Role();
		
		role.setDescricao("ROLE_USER");
		
		assertEquals(true, role.getId() == null);
		
		role = roleController.salvar(role).getBody();
		
		assertEquals(true, role.getId() > 0);
		
		
		Role role2 = roleRepository.findById(role.getId()).get();
		
		
		assertEquals(role.getId(), role2.getId());
	}

}
