package com.ferraro.uni.entity;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import jakarta.validation.constraints.Pattern;
import jakarta.persistence.Column;

@Entity
@Table(name = "docenti")
public class Docente {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@ManyToOne
	@JoinColumn(name = "cdl")
	private Cdl cdl;
	
	@Pattern(regexp = "^DC\\d{6}$") 
	@Column(nullable = false, unique = true)
	private String codiceDocente;
	
	@OneToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	@JoinColumn(name = "anagrafica_id", nullable = false, unique = true)
	private Anagrafica anagrafica;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Cdl getCdl() {
		return cdl;
	}

	public void setCdl(Cdl cdl) {
		this.cdl = cdl;
	}

	public String getCodiceDocente() {
		return codiceDocente;
	}

	public void setCodiceDocente(String codiceDocente) {
		this.codiceDocente = codiceDocente;
	}

	public Anagrafica getAnagrafica() {
		return anagrafica;
	}

	public void setAnagrafica(Anagrafica anagrafica) {
		this.anagrafica = anagrafica;
	}
	
	
	
	
}
