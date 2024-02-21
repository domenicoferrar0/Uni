package com.ferraro.uni.enums;

public enum Settore {

	INGEGNERIA("Ingegneria", "001"),
    MEDICINA("Medicina","002"),
    GIURISPRUDENZA("Giurisprudenza","003"),
    ECONOMIA("Economia","004"),
    LETTERE("Lettere","005"),
    INFORMATICA("Informatica", "006"),
    ARCHITETTURA("Architettura","007"),
    PSICOLOGIA("Psicologia", "008"),
    BIOLOGIA("Biologia","009"),
    STORIA("Storia", "010");

    private final String nome;
    private final String codice;
    
 Settore (String nome, String codice) {
        this.nome = nome;
        this.codice = codice;
    }

    public String getNomeCorso() {
        return nome;
    }
	
	public String getCodice() {
		return codice;
	}
}
