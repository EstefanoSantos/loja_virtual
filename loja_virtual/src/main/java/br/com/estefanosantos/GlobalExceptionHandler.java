package br.com.estefanosantos;

import java.util.List;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.oauth2.jwt.JwtEncodingException;
import org.springframework.security.oauth2.jwt.JwtException;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import br.com.estefanosantos.controller.dto.ObjectErroDto;

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
		} else {
			msg.append(ex.getMessage());
		}
		
		erroDto.setErro(msg.toString());
		erroDto.setStatus(status.toString());
		
		return new ResponseEntity<>(erroDto, HttpStatus.INTERNAL_SERVER_ERROR);
		
	} 

	@ExceptionHandler(BadCredentialsException.class)
	protected ResponseEntity<String> handleBadCredentialsException(BadCredentialsException ex) {
		return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Username or Password invalid.");
	}

	@ExceptionHandler(JwtEncodingException.class)
	protected ResponseEntity<String> handleJwtException(JwtException ex) {
		return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Cannot generate token.");
	}
		
}
 