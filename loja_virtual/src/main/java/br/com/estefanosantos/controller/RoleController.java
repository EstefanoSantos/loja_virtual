package br.com.estefanosantos.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;

import br.com.estefanosantos.model.Role;
import br.com.estefanosantos.service.RoleService;

@Controller
public class RoleController {

	@Autowired
	RoleService roleService;
	
	@PostMapping("/salvar-role")
	public void salvar(Role role) {
		roleService.salvarRole(role);
	}
}
