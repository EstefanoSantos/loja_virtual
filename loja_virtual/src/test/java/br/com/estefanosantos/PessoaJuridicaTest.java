package br.com.estefanosantos;

import java.util.Calendar;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Profile;

import br.com.estefanosantos.controller.PessoaJuridicaController;
import br.com.estefanosantos.enums.TipoEndereco;
import br.com.estefanosantos.exceptions.CustomException;
import br.com.estefanosantos.model.Endereco;
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
		pessoaJuridica.setEmail("Obiwan@email.com");
		pessoaJuridica.setContato("1232131231");
		pessoaJuridica.setInscricaoEstadual("asdsadsada");
		pessoaJuridica.setInscricaoMunicipal("asdasdsadsa");
		pessoaJuridica.setNomeFantasia("afqweqw");
		pessoaJuridica.setRazaoSocial("asadsadsad");
		pessoaJuridica.setCategoria("asdasdsad");
		
		Endereco endereco1 = new Endereco();
		endereco1.setBairro("Calungá");
		endereco1.setCep("222222");
		endereco1.setCidade("Boa Vista");
		endereco1.setComplemento("Caza Laranja");
		endereco1.setEmpresa(pessoaJuridica);
		endereco1.setNumero("1234");
		endereco1.setPessoa(pessoaJuridica);
		endereco1.setRua("Rua Caimbé");
		endereco1.setTipoEndereco(TipoEndereco.COBRANCA);
		endereco1.setUf("RR");
		
		Endereco endereco2 = new Endereco();
		endereco2.setBairro("Paraviana");
		endereco2.setCep("33333");
		endereco2.setCidade("Boa Vista");
		endereco2.setComplemento("Caza Cinza");
		endereco2.setEmpresa(pessoaJuridica);
		endereco2.setNumero("2345");
		endereco2.setPessoa(pessoaJuridica);
		endereco2.setRua("Rua Marajós");
		endereco2.setTipoEndereco(TipoEndereco.ENTREGA);
		endereco2.setUf("RR");
		
		pessoaJuridica.getEnderecos().add(endereco1);
		pessoaJuridica.getEnderecos().add(endereco2);
		
		pessoaJuridicaController.salvarPj(pessoaJuridica).getBody();
		
		assertEquals(true, pessoaJuridica.getId() > 0);
		
		for (Endereco endereco : pessoaJuridica.getEnderecos()) {
			assertEquals(true, endereco.getId() > 0);
		}
		
		assertEquals(2, pessoaJuridica.getEnderecos().size());
		
		
	}
}
