package com.ferraro.uni.exceptions;

public class PersonNotFoundException extends RuntimeException {
	
	public PersonNotFoundException(String cf) {
	    super("Nessuna corrispondenza trovata: " + cf);
	  }
}
