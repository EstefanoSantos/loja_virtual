package br.com.estefanosantos.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import br.com.estefanosantos.dto.CupDescontoDto;
import br.com.estefanosantos.exceptions.CustomException;
import br.com.estefanosantos.service.CupDescontoService;

@RestController
public class CupDescontoController {
	
	@Autowired
	private CupDescontoService cupDescontoService;
	
	@ResponseBody
	@GetMapping("/findAllByEmpresa/{idEmpresa}")
	public ResponseEntity<List<CupDescontoDto>> findAllByEmpresa(@PathVariable("idEmpresa") Long idEmpresa) throws CustomException {
		
		List<CupDescontoDto> list = cupDescontoService.findAllByEmpresa(idEmpresa);
		
		return new ResponseEntity<List<CupDescontoDto>>(list, HttpStatus.OK);
	}

}
