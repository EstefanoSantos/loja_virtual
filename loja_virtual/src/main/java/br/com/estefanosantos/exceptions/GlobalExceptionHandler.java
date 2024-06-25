package br.com.estefanosantos.exceptions;

import java.sql.SQLException;
import java.util.List;

import org.hibernate.exception.ConstraintViolationException;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.oauth2.jwt.JwtEncodingException;
import org.springframework.security.oauth2.jwt.JwtException;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import br.com.estefanosantos.dto.ObjectErroDto;

@ControllerAdvice
public class GlobalExceptionHandler extends ResponseEntityExceptionHandler {
	
	@ExceptionHandler({Exception.class, RuntimeException.class, Throwable.class})
	@Override
	protected ResponseEntity<Object> handleExceptionInternal(Exception ex, Object body, HttpHeaders headers,
			HttpStatusCode status, WebRequest request) {
		
		ObjectErroDto erroDto = new ObjectErroDto();
		
		StringBuilder msg = new StringBuilder();
		
		if (ex instanceof MethodArgumentNotValidException) {
			List<ObjectError> list = ((MethodArgumentNotValidException) ex).getBindingResult().getAllErrors();
			
			for (ObjectError objError : list) {
				msg.append(objError.getDefaultMessage()).append("\n");			
			}
		} else if (ex instanceof HttpMessageNotReadableException) {			
			msg.append("Corpo da requisição nulo ou mal formatado.");	
		} else {
			msg.append(ex.getMessage());
		}
		
		erroDto.setErro(msg.toString());
		erroDto.setStatus(status.toString());
		
		return new ResponseEntity<>(erroDto, HttpStatus.INTERNAL_SERVER_ERROR);
		
	} 
	
	@ExceptionHandler({DataIntegrityViolationException.class, ConstraintViolationException.class, SQLException.class})
	protected ResponseEntity<Object> handleDataIntegrityException(Exception ex) {
		
		ObjectErroDto erroDto = new ObjectErroDto();
		
		StringBuilder msg = new StringBuilder();
		
		if (ex instanceof DataIntegrityViolationException) {
			msg.append("Erro de integridade no banco: ").append(ex.getMessage());
		} else if (ex instanceof ConstraintViolationException) {
			msg.append("Erro de chave estrangeira: ").append(ex.getMessage());
		} else if (ex instanceof SQLException) {
			msg.append("Erro de Sql: ").append(ex.getMessage());
		} else {
			msg.append(ex.getMessage());
		}
		
		erroDto.setErro(msg.toString());
		erroDto.setStatus(HttpStatus.INTERNAL_SERVER_ERROR.toString());
			
		return new ResponseEntity<>(erroDto, HttpStatus.INTERNAL_SERVER_ERROR);
	}
	
	@ExceptionHandler(CustomException.class)
	public ResponseEntity<Object> handleCustomException(CustomException ex) {
		ObjectErroDto erroDto = new ObjectErroDto();
		
		erroDto.setErro(ex.getMessage());
		erroDto.setStatus(HttpStatus.OK.toString());
		
		return new ResponseEntity<>(erroDto, HttpStatus.OK);
	}

	@ExceptionHandler(BadCredentialsException.class)
	public ResponseEntity<String> handleBadCredentialsException(BadCredentialsException ex) {
		return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Username or Password invalid.");
	}

	@ExceptionHandler(JwtEncodingException.class)
	public ResponseEntity<String> handleJwtException(JwtException ex) {
		return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Cannot generate token.");
	}
		
}
 