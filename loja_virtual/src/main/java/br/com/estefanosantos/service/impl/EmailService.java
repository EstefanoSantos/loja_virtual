package br.com.estefanosantos.service.impl;

import java.io.UnsupportedEncodingException;
import java.util.Properties;

import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import jakarta.mail.Address;
import jakarta.mail.Authenticator;
import jakarta.mail.Message;
import jakarta.mail.MessagingException;
import jakarta.mail.PasswordAuthentication;
import jakarta.mail.Session;
import jakarta.mail.Transport;
import jakarta.mail.internet.InternetAddress;
import jakarta.mail.internet.MimeMessage;


@Service
public class EmailService {
	
	private String username = "estefanosantosbarros@gmail.com";
	
	private String senha = "zifhvyhpcgrmoyov";

	@Async
	public void sendEmailHtml(String destinatario, String assunto, String conteudo) throws UnsupportedEncodingException, MessagingException {
		
		Properties props = new Properties();
		props.put("mail.smtp.ssl.trust", "*");
		props.put("mail.smtp.auth", "true");
		props.put("mail.smtp.starttls", "false");
		props.put("mail.smtp.host", "smtp.gmail.com");
		props.put("mail.smtp.port", "465");
		props.put("mail.smtp.socketFactory.port", "465");
		props.put("mail.smtp.socketFactory.class", "javax.net.ssl.SSLSocketFactory");
		
		Session session = Session.getInstance(props, new Authenticator() {
			
			protected PasswordAuthentication getPasswordAuthentication() {
				return new PasswordAuthentication(username, senha);
			}
		});
		
		session.setDebug(true);
		
		Address[] toUser = InternetAddress.parse(destinatario);
		
		Message message = new MimeMessage(session);
		message.setFrom(new InternetAddress(username, "Loja Virtual Java", "UTF-8"));
		message.setRecipients(Message.RecipientType.TO, toUser);
		message.setSubject(assunto);
		message.setContent(conteudo, "text/html; charset=utf-8");
		
		Transport.send(message);
	}

}
