package br.com.estefanosantos.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import br.com.estefanosantos.dto.NotaFiscalVendaDto;
import br.com.estefanosantos.exceptions.CustomException;
import br.com.estefanosantos.service.NotaFiscalVendaService;

@RestController
public class NotaFiscalVendaController {
	
	@Autowired
	private NotaFiscalVendaService notaFiscalVendaService;
	
	@ResponseBody
	@GetMapping("/buscarNotaPorIdVenda/{idVenda}")
	public ResponseEntity<NotaFiscalVendaDto> buscarNotaPorIdVenda(@PathVariable("idVenda") Long idVenda) throws CustomException {
		
		NotaFiscalVendaDto notaFiscalVenda = notaFiscalVendaService.buscarNotaPorIdVenda(idVenda);
		
		return new ResponseEntity<NotaFiscalVendaDto>(notaFiscalVenda, HttpStatus.OK);
	}

}
