package br.com.estefanosantos.dto;
import java.util.Date;

public record CriarUsuarioDto(String login, String password, Date dataAttPassword, Long pessoaFisica) {
}
