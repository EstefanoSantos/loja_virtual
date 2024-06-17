package br.com.estefanosantos;

import java.util.Calendar;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Profile;

import br.com.estefanosantos.controller.PessoaJuridicaController;
import br.com.estefanosantos.exceptions.CustomException;
import br.com.estefanosantos.model.PessoaJuridica;
import junit.framework.TestCase;

@Profile("test")
@SpringBootTest(classes = LojaVirtualApplication.class)
public class PessoaJuridicaTest extends TestCase {
	
	@Autowired
	private PessoaJuridicaController pessoaJuridicaController;
	
	@Test
	public void testCadastroPessoaJurídica() throws CustomException {
		
		PessoaJuridica pessoaJuridica = new PessoaJuridica();
		
		pessoaJuridica.setCnpj("" + Calendar.getInstance().getTimeInMillis());
		pessoaJuridica.setNome("kaiasdasd");
		pessoaJuridica.setEmail("asdad@email.com");
		pessoaJuridica.setContato("1232131231");
		pessoaJuridica.setInscricaoEstadual("asdsadsada");
		pessoaJuridica.setInscricaoMunicipal("asdasdsadsa");
		pessoaJuridica.setNomeFantasia("afqweqw");
		pessoaJuridica.setRazaoSocial("asadsadsad");
		pessoaJuridica.setCategoria("asdasdsad");
		
		pessoaJuridicaController.salvarPj(pessoaJuridica);
		
		
	}
}
