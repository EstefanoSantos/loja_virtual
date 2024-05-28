package br.com.estefanosantos.service;

import br.com.estefanosantos.controller.dto.CriarUsuarioDto;
import br.com.estefanosantos.controller.dto.LoginDto;
import br.com.estefanosantos.model.Usuario;

public interface UsuarioService {

	String usuarioLogin(LoginDto dto) throws Exception;

	Usuario novoUsuario(CriarUsuarioDto dto);

}
