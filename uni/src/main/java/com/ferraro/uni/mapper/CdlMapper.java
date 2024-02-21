package com.ferraro.uni.mapper;

import java.util.List;
import java.util.stream.Collectors;

import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import com.ferraro.uni.dto.CdlDTO;
import com.ferraro.uni.dto.CdlDTOFull;
import com.ferraro.uni.dto.DocenteDTOList;
import com.ferraro.uni.dto.StudenteDTOList;
import com.ferraro.uni.entity.Cdl;
import com.ferraro.uni.entity.Docente;
import com.ferraro.uni.entity.Studente;
import com.ferraro.uni.entity.Anagrafica;

@Mapper(componentModel = "spring")
public interface CdlMapper {
	CdlMapper INSTANCE = Mappers.getMapper(CdlMapper.class);
	
	public Cdl dtoToCdl(CdlDTO cdlDto);
	
	
	public CdlDTOFull cdlToDtoFull(Cdl cdl);
	
	default List<CdlDTOFull> cdlsToDtoFull(List<Cdl> cdls){
		return cdls.stream().map(this::cdlToDtoFull).collect(Collectors.toList());
	}
	
	public CdlDTO cdlToDto(Cdl cdl);
	
	default List<CdlDTO> cdlToDtos(List<Cdl> cdls){
		return cdls.stream().map(this::cdlToDto).collect(Collectors.toList());
	}
	
	
//MAPPO DOCENTE E STUDENTE (hanno dei campi in più ma non hanno anagrafica) QUI PERCHE' SONO IL DTO SPECFICO DI CDLDTOFULL		
	 default StudenteDTOList studentToDTOList(Studente studente) {
		StudenteDTOList mStudente = new StudenteDTOList();
		Anagrafica anagrafica = studente.getAnagrafica();
		mStudente.setNome(anagrafica.getNome());
		mStudente.setCognome(anagrafica.getCognome());
		mStudente.setId(studente.getId());
		mStudente.setMatricola(studente.getMatricola());
		mStudente.setCf(anagrafica.getCf());
		return mStudente;
	}
	
	
	
	 default DocenteDTOList docenteToDTOList(Docente docente) {
		DocenteDTOList mDocente = new DocenteDTOList();
		Anagrafica anagrafica = docente.getAnagrafica();
		mDocente.setNome(anagrafica.getNome());
		mDocente.setCognome(anagrafica.getCognome());
		mDocente.setId(docente.getId());
		mDocente.setCodiceDocente(docente.getCodiceDocente());
		mDocente.setCf(anagrafica.getCf());
		return mDocente;
	}
	
}
