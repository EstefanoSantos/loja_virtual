package br.com.estefanosantos.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.estefanosantos.controller.dto.LoginDto;
import br.com.estefanosantos.service.UsuarioService;

@RestController
public class UserController {
	
	@Autowired
	private UsuarioService usuarioService;
	
	@PostMapping("/login")
	public ResponseEntity<String> login(LoginDto dto) throws Exception {
		
		String token = usuarioService.userLogin(dto);
		
		return new ResponseEntity<String>(token, HttpStatus.OK);
	}

}
