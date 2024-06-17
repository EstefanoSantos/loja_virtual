package br.com.estefanosantos.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import br.com.estefanosantos.dto.CriarUsuarioDto;
import br.com.estefanosantos.model.Usuario;
import br.com.estefanosantos.service.UsuarioService;

@RestController
public class UsuarioController {
	
	@Autowired
	private UsuarioService usuarioService;

	@PostMapping("/newUser")
	public ResponseEntity<Usuario> newUser(@RequestBody CriarUsuarioDto dto) {

		Usuario usuarioCriado = usuarioService.novoUsuario(dto);

		return new ResponseEntity<>(usuarioCriado, HttpStatus.OK);
	}

}
