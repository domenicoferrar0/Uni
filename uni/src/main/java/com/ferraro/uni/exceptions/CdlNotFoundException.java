package com.ferraro.uni.exceptions;

public class CdlNotFoundException extends RuntimeException {

	public CdlNotFoundException(String nomeCDL) {
		super("Nessuna corrispondenza trovata questo CDL: "+nomeCDL);
	}
}
