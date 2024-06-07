package br.com.estefanosantos.security;

import java.time.Instant;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.oauth2.jwt.JwtClaimsSet;
import org.springframework.security.oauth2.jwt.JwtEncoder;
import org.springframework.security.oauth2.jwt.JwtEncoderParameters;
import org.springframework.security.oauth2.jwt.JwtEncodingException;
import org.springframework.stereotype.Component;

@Component
public class JwtTokenUtil {

	@Autowired
	private JwtEncoder encoder;
	private static final String ISSUER = "loja_virtual";

	public String generateToken(Authentication authentication) {

		try {
			String scopes = authentication.getAuthorities().stream()
					.map(GrantedAuthority::getAuthority)
					.collect(Collectors.joining(" "));

			var claims = JwtClaimsSet.builder()
					.issuer(ISSUER)
					.issuedAt(creationTime())
					.expiresAt(expirationDate())
					.subject(authentication.getName())
					.claim("scope", scopes)
					.build();

			String token = encoder.encode(JwtEncoderParameters.from(claims)).getTokenValue();	
			
			return "{\"Authorization\": \""+ token + "\"}";
			
		} catch (JwtEncodingException e) {
			throw new RuntimeException();
		}
		
	}

	private Instant creationTime() {
		return ZonedDateTime.now(ZoneId.of("America/Sao_Paulo")).toInstant();
	}

	private Instant expirationDate() {
		return ZonedDateTime.now(ZoneId.of("America/Sao_Paulo")).plusDays(10).toInstant();
	}

}
