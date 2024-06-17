package br.com.estefanosantos.security;

import java.security.interfaces.RSAPrivateKey;
import java.security.interfaces.RSAPublicKey;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.oauth2.jwt.JwtDecoder;
import org.springframework.security.oauth2.jwt.JwtEncoder;
import org.springframework.security.oauth2.jwt.NimbusJwtDecoder;
import org.springframework.security.oauth2.jwt.NimbusJwtEncoder;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.security.web.SecurityFilterChain;

import com.nimbusds.jose.jwk.JWK;
import com.nimbusds.jose.jwk.JWKSet;
import com.nimbusds.jose.jwk.RSAKey;
import com.nimbusds.jose.jwk.source.ImmutableJWKSet;
import com.nimbusds.jose.jwk.source.JWKSource;
import com.nimbusds.jose.proc.SecurityContext;


@Configuration
@EnableWebSecurity
public class WebConfigSecurity {

	@Value("${jwt.public.key}")
	private RSAPublicKey publicKey;

	@Value("${jwt.private.key}")
	private RSAPrivateKey privateKey;
	
	AuthenticationEntryPoint entryPoint;
	
	public WebConfigSecurity(@Qualifier("jwtAuthEntryPoint") AuthenticationEntryPoint entryPoint) {
		this.entryPoint = entryPoint;
	}
		
	@Bean
	SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
		
		http
				.csrf(AbstractHttpConfigurer::disable)
				.authorizeHttpRequests(
						authorize ->  authorize
								.requestMatchers(HttpMethod.POST, "/auth").permitAll()
								.requestMatchers(HttpMethod.POST, "/salvarPj").permitAll()
								.anyRequest().authenticated()
				)
				.httpBasic(Customizer.withDefaults())
				.oauth2ResourceServer(oauth2 -> oauth2
						.authenticationEntryPoint(entryPoint)
		                .jwt(jwt -> jwt
		                    .decoder(decoder()))		             
		            )
				.sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS));
				return http.build();

	}
	
	@Bean
	JwtDecoder decoder() {		
			return NimbusJwtDecoder.withPublicKey(this.publicKey).build();				
	}	

	@Bean
	JwtEncoder encoder() {
		JWK jwk = new RSAKey.Builder(this.publicKey)
		.privateKey(this.privateKey)
		.build();
		
		JWKSource<SecurityContext> jwks = new ImmutableJWKSet<>(new JWKSet(jwk));
		return new NimbusJwtEncoder(jwks);
	}
	
	@Bean
	BCryptPasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}	
}
