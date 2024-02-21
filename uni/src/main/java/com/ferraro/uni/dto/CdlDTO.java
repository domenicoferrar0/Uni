package com.ferraro.uni.dto;

import com.ferraro.uni.enums.Settore;

import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.Size;

public class CdlDTO {
	
	@NotNull
	@Positive(message = "Il numero massimo di studenti deve essere positivo")
	private Integer maxStudenti;

	private Long id;
	
	@NotNull
	@Size(min = 5, max = 50, message = "Questo campo può avere un minimo di 5 ed un massimo di 50 caratteri")
	@Pattern(regexp="\\S(\\s*[a-zA-Z]+)*\\s*", message = "Formato nome non valido, rimuovi gli spazi in eccesso e i caratteri non autorizzati")
	private String nome;
	
	@NotNull
	@Enumerated(EnumType.STRING)
	private Settore settore;

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
	
}
