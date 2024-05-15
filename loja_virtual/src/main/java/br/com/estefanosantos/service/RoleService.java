package br.com.estefanosantos.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.estefanosantos.model.Role;
import br.com.estefanosantos.repository.RoleRepository;

@Service
public class RoleService {
	
	@Autowired
	RoleRepository roleRepository;
	
	public Role salvarRole(Role role) {
		return roleRepository.save(role);
	}
	
	public void apagarRole(Long id) {
		roleRepository.deleteById(id);
	}
}
