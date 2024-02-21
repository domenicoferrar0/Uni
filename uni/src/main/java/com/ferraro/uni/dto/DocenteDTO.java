package com.ferraro.uni.dto;

public class DocenteDTO {
	
	
	private Long id;
		
	private CdlDTO cdlDTO;	
	
	private String codiceDocente;	
	
	private AnagraficaDTO anagraficaDTO;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public CdlDTO getCdlDTO() {
		return cdlDTO;
	}

	public void setCdlDTO(CdlDTO cdlDTO) {
		this.cdlDTO = cdlDTO;
	}

	public String getCodiceDocente() {
		return codiceDocente;
	}

	public void setCodiceDocente(String codiceDocente) {
		this.codiceDocente = codiceDocente;
	}

	public AnagraficaDTO getAnagraficaDTO() {
		return anagraficaDTO;
	}

	public void setAnagraficaDTO(AnagraficaDTO anagraficaDTO) {
		this.anagraficaDTO = anagraficaDTO;
	}
	
	
}
