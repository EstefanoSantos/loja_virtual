package br.com.estefanosantos.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.estefanosantos.exceptions.CustomException;
import br.com.estefanosantos.model.Role;
import br.com.estefanosantos.repository.RoleRepository;
import br.com.estefanosantos.service.RoleService;

@Service
public class RoleServiceImpl implements RoleService {
	
	@Autowired
	RoleRepository roleRepository;
	
	@Override
	public Role salvarRole(Role role) throws CustomException {
		
		if (role.getId() != null) {
			throw new CustomException("Corpo da requisição não deve conter Id.");
		}
		List<Role> roles = roleRepository.buscarPorDesc(role.getDescricao().toUpperCase());
		if (!roles.isEmpty()) {
			throw new CustomException("Essa role já está cadastrada.");	
		}	
		 return roleRepository.save(role);
	}
	
	@Override
	public void apagarRole(Long id) {
		roleRepository.deleteById(id);
	}
	
	@Override
	public Role buscarRole(Long id) throws CustomException {
		
		Role role = roleRepository.findById(id).orElse(null);
		
		if (role == null) {
			throw new CustomException("Não foi encontrado a role com id: " +id);
		}
		
		return role;
	}
	
	@Override
	public List<Role> buscarPorDesc(String desc) {
		return roleRepository.buscarPorDesc(desc);	
	}
}
