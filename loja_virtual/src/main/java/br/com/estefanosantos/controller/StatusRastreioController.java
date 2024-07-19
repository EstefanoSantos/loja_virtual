package br.com.estefanosantos.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import br.com.estefanosantos.dto.StatusRastreioDto;
import br.com.estefanosantos.exceptions.CustomException;
import br.com.estefanosantos.service.StatusRastreioService;

@RestController
public class StatusRastreioController {
	
	@Autowired
	private StatusRastreioService statusRastreioService;
	
	@ResponseBody
	@GetMapping("/buscarRastreioVenda/{idVenda}")
	public ResponseEntity<List<StatusRastreioDto>> buscarRastreioVenda(@PathVariable("idVenda") Long idVenda) throws CustomException {
		
		List<StatusRastreioDto> listDto = statusRastreioService.buscarRastreioVenda(idVenda);
		
		return new ResponseEntity<List<StatusRastreioDto>>(listDto, HttpStatus.OK);
	}

}
