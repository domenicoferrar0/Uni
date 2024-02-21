package com.ferraro.uni.dto;

public class DocenteDTOList {
	
	private Long id;

	private String nome;

	private String cognome;
	
	private String codiceDocente;
	
	private String cf;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getCognome() {
		return cognome;
	}

	public void setCognome(String cognome) {
		this.cognome = cognome;
	}

	public String getCodiceDocente() {
		return codiceDocente;
	}

	public void setCodiceDocente(String codiceDocente) {
		this.codiceDocente = codiceDocente;
	}

	public String getCf() {
		return cf;
	}

	public void setCf(String cf) {
		this.cf = cf;
	}
	
	
}
