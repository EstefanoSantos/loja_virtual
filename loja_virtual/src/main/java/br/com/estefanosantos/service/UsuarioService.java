package br.com.estefanosantos.service;

import br.com.estefanosantos.dto.CriarUsuarioDto;
import br.com.estefanosantos.model.Usuario;

public interface UsuarioService {

	Usuario novoUsuario(CriarUsuarioDto dto);

}
