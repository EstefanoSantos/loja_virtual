package br.com.estefanosantos.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import br.com.estefanosantos.model.Role;
import br.com.estefanosantos.service.RoleService;

@RestController
public class RoleController {

	@Autowired
	RoleService roleService;
	
	@ResponseBody
	@PostMapping("/salvar-role")
	public ResponseEntity<Role> salvar(@RequestBody Role role) {
		
		Role roleSaved = roleService.salvarRole(role);
		
		return new ResponseEntity<Role>(roleSaved, HttpStatus.OK); 
	}
	
	@ResponseBody
	@PostMapping("/apagar-role")
	public ResponseEntity<String> apagar(@RequestParam Long id) {
		
		roleService.apagarRole(id);
		
		return new ResponseEntity<>("Registro removido!", HttpStatus.OK);
	}
}
