package br.com.estefanosantos.controller;

import br.com.estefanosantos.controller.dto.CriarUsuarioDto;
import br.com.estefanosantos.model.Usuario;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import br.com.estefanosantos.controller.dto.LoginDto;
import br.com.estefanosantos.service.UsuarioService;

@RestController
public class UsuarioController {
	
	@Autowired
	private UsuarioService usuarioService;
	
	@PostMapping("/auth")
	public ResponseEntity<String> login(@RequestBody LoginDto dto) throws Exception {
		
		String token = usuarioService.usuarioLogin(dto);
		
		return new ResponseEntity<>(token, HttpStatus.OK);
	}

	@PostMapping("/newUser")
	public ResponseEntity<Usuario> newUser(@RequestBody CriarUsuarioDto dto) {

		Usuario usuarioCriado = usuarioService.novoUsuario(dto);

		return new ResponseEntity<>(usuarioCriado, HttpStatus.OK);
	}

}
