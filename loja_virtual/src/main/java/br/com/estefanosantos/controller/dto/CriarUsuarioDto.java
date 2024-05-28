package br.com.estefanosantos.controller.dto;
import br.com.estefanosantos.model.PessoaFisica;

import java.util.Date;

public record CriarUsuarioDto(String login, String password, Date dataAttPassword, Long pessoaFisica) {
}
