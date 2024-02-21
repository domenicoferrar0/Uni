package com.ferraro.uni.dto;

import java.sql.Date;

import com.ferraro.uni.enums.Genere;
import com.ferraro.uni.enums.Provincia;

import jakarta.persistence.Column;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Past;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;

public class Form {
	@Size(min = 2, max = 35, message = "Questo campo deve avere un massimo di 35 caratteri ed un minimo di 2")
    @Pattern(regexp="\\S(\\s*[a-zA-Z]+)*\\s*", message = "Formato nome non valido, rimuovi gli spazi in eccesso e i caratteri non autorizzati")
    private String nome;

    @Size(min = 2, max = 35, message = "Questo campo deve avere un massimo di 35 caratteri ed un minimo di 2")
    @Pattern(regexp="\\S(\\s*[a-zA-Z]+)*\\s*", message = "Formato nome non valido, rimuovi gli spazi in eccesso e i caratteri non autorizzati")
    private String cognome;
    
    @Past(message = "La data di nascita non può essere nel futuro")
    @NotNull(message = "La data di nascita è obbligatoria")
    private Date nascita;

    @Pattern(regexp ="^(?:[A-Z][AEIOU][AEIOUX]|[AEIOU]X{2}|"
            + "[B-DF-HJ-NP-TV-Z]{2}[A-Z]){2}(?:[\\dLMNP-V]{2}(?:[A-EHLMPR-T](?:[04LQ][1-9MNP-V]|[15MR][\\dLMNP-V]|[26NS][0-8LMNP-U])|[DHPS][37PT][0L]|[ACELMRT][37PT][01LM]|[AC-EHLMPR-T][26NS][9V])|(?:[02468LNQSU][048LQU]|[13579MPRTV][26NS])B[26NS][9V])(?:[A-MZ][1-9MNP-V][\\dLMNP-V]{2}|[A-M][0L](?:[1-9MNP-V][\\dLMNP-V]|[0L][1-9MNP-V]))[A-Z]$", message ="Formato Codice Fiscale non valido")
    private String cf;

    @NotNull(message = "L'indirizzo è obbligatorio")
    private String indirizzo;

    @NotNull(message = "Il genere è obbligatorio")
    private Genere genere;
    
    @Enumerated(EnumType.STRING)
	 @Column(nullable = false)
    private Provincia luogoDiNascita;
    
    private String nomeCdl;

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

	public String getNomeCdl() {
		return nomeCdl;
	}

	public Provincia getLuogoDiNascita() {
		return luogoDiNascita;
	}

	public void setLuogoDiNascita(Provincia luogoDiNascita) {
		this.luogoDiNascita = luogoDiNascita;
	}
    
    
}
