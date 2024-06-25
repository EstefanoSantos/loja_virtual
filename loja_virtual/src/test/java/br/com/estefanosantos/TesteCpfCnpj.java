package br.com.estefanosantos;

import br.com.estefanosantos.util.ValidaCnpj;
import br.com.estefanosantos.util.ValidaCpf;

public class TesteCpfCnpj {
	
	public static void main(String[] args) {
		
		//boolean isValid = ValidaCnpj.isCNPJ("00.621.708/0001-60");
		
		//System.out.println(isValid);
		
		boolean isValid = ValidaCpf.isCPF("521.793.570-79");
		
		System.out.println(isValid);
	}

}
