package com.ferraro.uni.entity;

import java.sql.Date;


import com.ferraro.uni.enums.Genere;
import com.ferraro.uni.enums.Provincia;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;
import jakarta.validation.constraints.Past;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;

@Table(name = "anagrafica")
@Entity
public class Anagrafica {

	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@Size(min = 2, max = 35, message = "Formato non valid, minimo 2 caratteri, massimo 35")
	@Pattern(regexp="\\S(\\s*[a-zA-Z]+)*\\s*", message = "Formato nome non valido, rimuovi gli spazi in eccesso e i caratteri non autorizzati")
	@Column(nullable = false)
	private String nome;
	
	@Size(min = 2, max = 35, message = "Formato non valido, minimo 2 caratteri, massimo 35")
	@Pattern(regexp="\\S(\\s*[a-zA-Z]+)*\\s*", message = "Formato nome non valido, rimuovi gli spazi in eccesso e i caratteri non autorizzati")
	@Column(nullable = false)
	private String cognome;
	
	
	@Past(message = "La data di nascita non può essere nel futuro")
	@Temporal(TemporalType.DATE)
	@Column(nullable = false)
	private Date nascita;
		
	@Column(nullable = false, unique = true)
	@Pattern(regexp ="^(?:[A-Z][AEIOU][AEIOUX]|[AEIOU]X{2}|"
		    + "[B-DF-HJ-NP-TV-Z]{2}[A-Z]){2}(?:[\\dLMNP-V]{2}(?:[A-EHLMPR-T](?:[04LQ][1-9MNP-V]|[15MR][\\dLMNP-V]|[26NS][0-8LMNP-U])|[DHPS][37PT][0L]|[ACELMRT][37PT][01LM]|[AC-EHLMPR-T][26NS][9V])|(?:[02468LNQSU][048LQU]|[13579MPRTV][26NS])B[26NS][9V])(?:[A-MZ][1-9MNP-V][\\dLMNP-V]{2}|[A-M][0L](?:[1-9MNP-V][\\dLMNP-V]|[0L][1-9MNP-V]))[A-Z]$", message ="Formato CF non valido")
	private String cf;
	
	@Column(nullable = false)
	private String indirizzo;
		
	
	@Enumerated(EnumType.STRING)
 	@Column(nullable = false)
	private Genere genere;
	
	@Enumerated(EnumType.STRING)
 	@Column(nullable = false)
	private Provincia luogoDiNascita;

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


	public Date getNascita() {
		return nascita;
	}


	public void setNascita(Date nascita) {
		this.nascita = nascita;
	}


	public String getCf() {
		return cf;
	}


	public void setCf(String cf) {
		this.cf = cf;
	}


	public String getIndirizzo() {
		return indirizzo;
	}


	public void setIndirizzo(String indirizzo) {
		this.indirizzo = indirizzo;
	}


	public Genere getGenere() {
		return genere;
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
