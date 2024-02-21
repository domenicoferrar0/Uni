package com.ferraro.uni.enums;

public enum Genere {
	
	M("Maschio"),
    F("Femmina"),
    O("Other"),
    ND("Non dichiarato");

    private final String nome;

    Genere(String nome) {
        this.nome = nome;
    }

    public String getNome() {
        return nome;
    }
}
	
	


