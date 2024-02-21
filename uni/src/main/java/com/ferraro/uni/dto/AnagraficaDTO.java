package com.ferraro.uni.dto;

import java.sql.Date;

import com.ferraro.uni.enums.Genere;
import com.ferraro.uni.enums.Provincia;

public class AnagraficaDTO {

	private Long id;

	private String nome;

	private String cognome;

	private Date nascita;

	private String cf;

	private String indirizzo;

	private Genere genere;
	
	private Provincia luogoDiNascita;			

	public Long getId() {
		return id;
	}

	public String getNome() {
		return nome;
	}

	public String getCognome() {
		return cognome;
	}

	public Date getNascita() {
		return nascita;
	}

	public String getCf() {
		return cf;
	}

	public String getIndirizzo() {
		return indirizzo;
	}

	public Genere getGenere() {
		return genere;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public void setCognome(String cognome) {
		this.cognome = cognome;
	}

	public void setNascita(Date nascita) {
		this.nascita = nascita;
	}

	public void setCf(String cf) {
		this.cf = cf;
	}

	public void setIndirizzo(String indirizzo) {
		this.indirizzo = indirizzo;
	}

	public void setGenere(Genere genere) {
		this.genere = genere;
	}

	public Provincia getLuogoDiNascita() {
		return luogoDiNascita;
	}

	public void setLuogoDiNascita(Provincia luogoDiNascita) {
		this.luogoDiNascita = luogoDiNascita;
	}
	
	
	
}
