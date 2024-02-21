package com.ferraro.uni.entity;

import java.util.Set;

import com.ferraro.uni.enums.Settore;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import jakarta.validation.constraints.Positive;

@Entity
@Table(name = "Corsi_di_Laurea")
public class Cdl {
	
	@Column(nullable = false)
	@Positive(message = "Il numero massimo di studenti deve essere positivo")
	private Integer maxStudenti;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@Size(min = 5, max = 50, message = "Questo campo può avere un minimo di 5 caratteri ed un massimo di 50 caratteri")
	@Pattern(regexp="\\S(\\s*[a-zA-Z]+)*\\s*", message = "Formato nome non valido, rimuovi gli spazi in eccesso e i caratteri non autorizzati")
	@Column(nullable = false, unique = true)
	private String nome;
	
	@Enumerated(EnumType.STRING)
	 @Column(nullable = false)
	private Settore settore;
	
	@OneToMany(mappedBy = "cdl", fetch = FetchType.LAZY, cascade = jakarta.persistence.CascadeType.PERSIST)
	private Set<Studente> studenti;
	
	@OneToMany(mappedBy = "cdl", fetch = FetchType.LAZY, cascade = jakarta.persistence.CascadeType.PERSIST)
	private Set<Docente> docenti;
		
	
	public Set<Docente> getDocenti() {
		return docenti;
	}

	public void setDocenti(Set<Docente> docenti) {
		this.docenti = docenti;
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

	public Set<Studente> getStudenti() {
		return studenti;
	}

	public void setStudenti(Set<Studente> studenti) {
		this.studenti = studenti;
	}

	public int getMaxStudenti() {
		return maxStudenti;
	}
	
	public String getCodice() {
		return settore.getCodice();
	}

	
	
	
	
}
