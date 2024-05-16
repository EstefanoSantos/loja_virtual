package br.com.estefanosantos.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.estefanosantos.model.Role;
import br.com.estefanosantos.repository.RoleRepository;
import br.com.estefanosantos.service.RoleService;

@Service
public class RoleServiceImpl implements RoleService {
	
	@Autowired
	RoleRepository roleRepository;
	
	@Override
	public Role salvarRole(Role role) {
		return roleRepository.save(role);
	}
	
	@Override
	public void apagarRole(Long id) {
		roleRepository.deleteById(id);
	}
	
	@Override
	public Role buscarRole(Long id) {
		return roleRepository.findById(id).get();
	}
	
	@Override
	public List<Role> buscarPorDesc(String desc) {
		return roleRepository.buscarPorDesc(desc);	
	}
}
