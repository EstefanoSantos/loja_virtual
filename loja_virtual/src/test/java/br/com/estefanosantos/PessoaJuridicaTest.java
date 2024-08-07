package br.com.estefanosantos;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Profile;

import br.com.estefanosantos.controller.PessoaFisicaController;
import br.com.estefanosantos.controller.PessoaJuridicaController;
import br.com.estefanosantos.enums.TipoEndereco;
import br.com.estefanosantos.exceptions.CustomException;
import br.com.estefanosantos.model.Endereco;
import br.com.estefanosantos.model.PessoaFisica;
import br.com.estefanosantos.model.PessoaJuridica;
import br.com.estefanosantos.repository.PessoaFisicaRepository;
import br.com.estefanosantos.repository.PessoaJuridicaRepository;
import junit.framework.TestCase;

@Profile("test")
@SpringBootTest(classes = LojaVirtualApplication.class)
public class PessoaJuridicaTest extends TestCase {
	
	@Autowired
	private PessoaJuridicaController pessoaJuridicaController;
	
	@Autowired
	private PessoaJuridicaRepository pessoaJuridicaRepository;
	
	@Autowired
	private PessoaFisicaController pessoaFisicaController;
	
	@Autowired
	private PessoaFisicaRepository pessoaFisicaRepository;
	
	@Test
	public void buscarUsuariosPorNome() {
		
		String nome = "tot";
		
		List<PessoaFisica> nomes = pessoaFisicaRepository.buscarPorNome(nome);
		
		for (PessoaFisica pessoa: nomes) {
			assertTrue(pessoa.getNome().contains(nome));
		}
	}
	
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
		//endereco1.setPessoa(pessoaJuridica);
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
		//endereco2.setPessoa(pessoaJuridica);
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
	
	
	@Test
	public void testCadastroPessoaFisica() throws CustomException, ParseException {
		
		PessoaJuridica pj = pessoaJuridicaRepository.existeCnpj("189533234511733213");
		
		System.out.println(pj.getNome());
		
		PessoaFisica pessoaFisica = new PessoaFisica();
		
		pessoaFisica.setCpf("678.451.080-14");
		pessoaFisica.setNome("kaiasdasd");
		pessoaFisica.setEmail("kartolasantos@email.com");
		pessoaFisica.setContato("1232131231");
		pessoaFisica.setDataNascimento(new SimpleDateFormat("yyyy-MM-dd").parse("1998-09-03"));
		pessoaFisica.setEmpresa(pj);
		
		Endereco endereco1 = new Endereco();
		endereco1.setBairro("Calungá");
		endereco1.setCep("222222");
		endereco1.setCidade("Boa Vista");
		endereco1.setComplemento("Caza Laranja");
		endereco1.setEmpresa(pj);
		endereco1.setNumero("1234");
		endereco1.setPessoa(pessoaFisica);
		endereco1.setRua("Rua Caimbé");
		endereco1.setTipoEndereco(TipoEndereco.COBRANCA);
		endereco1.setUf("RR");
		
		Endereco endereco2 = new Endereco();
		endereco2.setBairro("Paraviana");
		endereco2.setCep("33333");
		endereco2.setCidade("Boa Vista");
		endereco2.setComplemento("Caza Cinza");
		endereco2.setEmpresa(pj);
		endereco2.setNumero("2345");
		endereco2.setPessoa(pessoaFisica);
		endereco2.setRua("Rua Marajós");
		endereco2.setTipoEndereco(TipoEndereco.ENTREGA);
		endereco2.setUf("RR");
		
		pessoaFisica.getEnderecos().add(endereco1);
		pessoaFisica.getEnderecos().add(endereco2);
		
		//pessoaFisica = pessoaFisicaController.salvarPf(pessoaFisica).getBody();
		
		assertEquals(true, pessoaFisica.getId() > 0);

	}
}
