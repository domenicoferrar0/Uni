package com.ferraro.uni.dto;

import java.util.Set;


import com.ferraro.uni.enums.Settore;


public class CdlDTOFull {
	private Integer maxStudenti;

	private Long id;

	private String nome;

	private Settore settore;

	private Set<StudenteDTOList> studenti;

	private Set<DocenteDTOList> docenti;

	public Integer getMaxStudenti() {
		return maxStudenti;
	}

	public void setMaxStudenti(Integer maxStudenti) {
		this.maxStudenti = maxStudenti;
	}

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

	public Settore getSettore() {
		return settore;
	}

	public void setSettore(Settore settore) {
		this.settore = settore;
	}

	public Set<StudenteDTOList> getStudenti() {
		return studenti;
	}

	public void setStudenti(Set<StudenteDTOList> studenti) {
		this.studenti = studenti;
	}

	public Set<DocenteDTOList> getDocenti() {
		return docenti;
	}

	public void setDocenti(Set<DocenteDTOList> docenti) {
		this.docenti = docenti;
	}
}
