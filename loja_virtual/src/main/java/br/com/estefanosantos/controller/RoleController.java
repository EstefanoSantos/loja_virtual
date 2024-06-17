package br.com.estefanosantos.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import br.com.estefanosantos.exceptions.CustomException;
import br.com.estefanosantos.model.Role;
import br.com.estefanosantos.service.impl.RoleServiceImpl;

@RestController
public class RoleController {

	@Autowired
	RoleServiceImpl roleService;
	
	@ResponseBody
	@PostMapping(value = "/salvarRole")
	public ResponseEntity<Role> salvar(@RequestBody Role role) throws CustomException {
		
		Role roleSaved = roleService.salvarRole(role);
		
		return new ResponseEntity<Role>(roleSaved, HttpStatus.OK); 
	}
	
	@ResponseBody
	@DeleteMapping("/apagarRole/{id}")
	public ResponseEntity<String> apagar(@PathVariable("id") Long id) {
		
		roleService.apagarRole(id);
		
		return new ResponseEntity<>("Registro removido!", HttpStatus.OK);
	}
	
	@ResponseBody
	@GetMapping("/buscarRole/{id}")
	public ResponseEntity<Role> buscarPorId(@PathVariable("id") Long id) throws CustomException {
		
		var role = roleService.buscarRole(id);
		
		return new ResponseEntity<Role>(role, HttpStatus.OK);
	}
	
	@ResponseBody
	@GetMapping("/buscarPorDesc/{desc}")
	public ResponseEntity<List<Role>> buscarPorDesc(@PathVariable("desc") String desc) {
		
		List<Role> roles = roleService.buscarPorDesc(desc);
		
		return new ResponseEntity<List<Role>>(roles, HttpStatus.OK);
	}
}
