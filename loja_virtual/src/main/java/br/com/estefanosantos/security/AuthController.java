package br.com.estefanosantos.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import br.com.estefanosantos.dto.LoginDto;

@RestController
public class AuthController {
	
	@Autowired
	private AuthService authService;
	
	@PostMapping("/auth")
	public ResponseEntity<String> login(@RequestBody LoginDto dto) throws Exception {
		
		String token = authService.usuarioLogin(dto);
		
		return new ResponseEntity<>(token, HttpStatus.OK);
	}
}
