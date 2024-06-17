package br.com.estefanosantos.service;

import java.util.List;

import br.com.estefanosantos.exceptions.CustomException;
import br.com.estefanosantos.model.Role;

public interface RoleService {
	
	public Role salvarRole(Role role) throws CustomException;
	
	public void apagarRole(Long id);
	
	public Role buscarRole(Long id) throws CustomException;
	
	public List<Role> buscarPorDesc(String desc);
}
