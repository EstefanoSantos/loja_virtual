package br.com.estefanosantos.security;

import java.util.Date;

import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

import br.com.estefanosantos.ApplicationContextLoad;
import br.com.estefanosantos.model.Usuario;
import br.com.estefanosantos.repository.UsuarioRepository;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@Service
public class JWTTokenAutenticacaoService {
	
	private static final Long EXPIRATION_TIME = 864000000L;
	
	private static final String SECRET = "ss/-2321321asdsa431-/-32";
	
	private static final String TOKEN_PREFIX = "Bearer";
	
	private static final String HEADER_STRING = "Authorization";
	
	/*Criação do Token JWT*/
	public void addAuthentication(HttpServletResponse response, String username) throws Exception {
		
		String JWT = Jwts.builder()
					 .setSubject(username)
					 .setExpiration(new Date(System.currentTimeMillis() + EXPIRATION_TIME))
					 .signWith(SignatureAlgorithm.HS512, SECRET).compact();
		
		String token = TOKEN_PREFIX + " " + JWT;
		
		response.setHeader(HEADER_STRING, token);
		
		liberacaoCors(response);
		
		response.getWriter().write("{\"Authorization\": \"" + token + "\"}");
	}
	
	/*Retorna o usuário válido com token ou null caso não seja válido*/
	public Authentication getAuthentication(HttpServletRequest request, HttpServletResponse response) {
		
		String token = request.getHeader(HEADER_STRING);
		
		if (token != null) {
			
			String tokenLimpo = token.replaceAll(TOKEN_PREFIX, "").trim();
			
			String user = Jwts.parser()
						 .setSigningKey(SECRET)
						 .parseClaimsJws(tokenLimpo)
						 .getBody().getSubject();
			
			if (user != null) {
				
				Usuario usuario = ApplicationContextLoad.getApplicationContext()
								 .getBean(UsuarioRepository.class).findUserByLogin(user);
				
				if (usuario != null) {
					return new UsernamePasswordAuthenticationToken(
							   usuario.getUsername(),
							   usuario.getPassword(),
							   usuario.getAuthorities());
				}
				
			}
		}
		
		liberacaoCors(response);
		return null;
	}
	
	
	/*Fazedo liberação de cors no navegador*/
	private void liberacaoCors(HttpServletResponse response) {
		
		if (response.getHeader("Access-Control-Allow-Origin") == null){
			response.addHeader("Access-Control-Allow-Origin", "*");
		}
		
		if (response.getHeader("Access-Control-Allow-Headers") == null){
			response.addHeader("Access-Control-Allow-Headers", "*");
		}

		if (response.getHeader("Access-Control-Request-Headers") == null){
			response.addHeader("Access-Control-Request-Headers", "*");
		}
		
		if (response.getHeader("Access-Control-Allow-Methods") == null){
			response.addHeader("Access-Control-Allow-Methods", "*");
		}
	}

}
