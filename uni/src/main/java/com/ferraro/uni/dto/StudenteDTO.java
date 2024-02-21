package com.ferraro.uni.dto;

public class StudenteDTO {
	
	
	private Long id;
		
	private String matricola;
		
	private CdlDTO cdlDTO;
		
	private AnagraficaDTO anagraficaDTO;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getMatricola() {
		return matricola;
	}

	public void setMatricola(String matricola) {
		this.matricola = matricola;
	}

	public CdlDTO getCdlDTO() {
		return cdlDTO;
	}

	public void setCdlDTO(CdlDTO cdlDTO) {
		this.cdlDTO = cdlDTO;
	}

	public AnagraficaDTO getAnagraficaDTO() {
		return anagraficaDTO;
	}

	public void setAnagraficaDTO(AnagraficaDTO anagraficaDTO) {
		this.anagraficaDTO = anagraficaDTO;
	}
	

	
	
	
}
