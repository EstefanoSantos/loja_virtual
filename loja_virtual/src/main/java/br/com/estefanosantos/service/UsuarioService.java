package br.com.estefanosantos.service;

import br.com.estefanosantos.controller.dto.LoginDto;

public interface UsuarioService {
	
	String userLogin(LoginDto dto) throws Exception;

}
