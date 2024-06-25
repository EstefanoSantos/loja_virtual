package br.com.estefanosantos.service.impl;

import java.io.UnsupportedEncodingException;
import java.time.LocalDate;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import br.com.estefanosantos.model.Usuario;
import br.com.estefanosantos.repository.UsuarioRepository;
import jakarta.mail.MessagingException;

@Service
public class TarefasAutomatizadasService {
	
	@Autowired
	private UsuarioRepository usuarioRepository;
	
	@Autowired
	private EmailService emailService;
	
	
	//@Scheduled(initialDelay = 2000, fixedDelay = 86400000) //utilizado para testar a execução do método
	@Scheduled(cron = "0 0 11 * * *")
	public void notificarUsuarioTrocaSenha() throws UnsupportedEncodingException, MessagingException, InterruptedException {
		
		LocalDate date = LocalDate.now().minusDays(90);
		List<Usuario> usuarios = usuarioRepository.usuarioSenhaVencida(date);
		
		if (usuarios != null) {
			
			StringBuilder msg = new StringBuilder();
			
			for (Usuario usuario : usuarios) {
				
				msg.append("<b>Olá, ").append(usuario.getPessoa().getNome()).append("</b><br/>");
				msg.append("<p>90 dias se passaram e precisamos que atualize sua senha!</p>");
				msg.append("<p>Agradecido, Loja Virtual Java</p>");
				
				emailService.sendEmailHtml(usuario.getPessoa().getEmail(), "Troca de Senha", msg.toString());
				
				msg.setLength(0);
						
				Thread.sleep(3000);	
			}
		}
	}

}
