package br.com.estefanosantos.service;

import java.util.List;

import br.com.estefanosantos.model.Role;

public interface RoleService {
	
	public Role salvarRole(Role role);
	
	public void apagarRole(Long id);
	
	public Role buscarRole(Long id);
	
	public List<Role> buscarPorDesc(String desc);
}
