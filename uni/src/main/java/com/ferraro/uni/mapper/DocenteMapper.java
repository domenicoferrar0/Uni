package com.ferraro.uni.mapper;

import java.util.List;
import java.util.stream.Collectors;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

import com.ferraro.uni.dto.DocenteDTO;
import com.ferraro.uni.dto.Form;
import com.ferraro.uni.entity.Anagrafica;
import com.ferraro.uni.entity.Docente;

@Mapper(componentModel = "spring")
public interface DocenteMapper {
	
	DocenteMapper INSTANCE = Mappers.getMapper(DocenteMapper.class);
	
	@Mapping(source = "docente.anagrafica", target = "anagraficaDTO")
	@Mapping(source = "docente.cdl", target = "cdlDTO")
	public DocenteDTO docenteToDTO(Docente docente);
	
	default List<DocenteDTO> docentiToDto(List<Docente> docenti){
		return docenti.stream().map(this::docenteToDTO).collect(Collectors.toList());
	}
	
	default Docente formToDocente(Form form) {
		Anagrafica anagrafica = new Anagrafica();
		anagrafica.setLuogoDiNascita(form.getLuogoDiNascita());
		anagrafica.setNome(form.getNome());
		anagrafica.setCognome(form.getCognome());
		anagrafica.setNascita(form.getNascita());
		anagrafica.setIndirizzo(form.getIndirizzo());
		anagrafica.setCf(form.getCf());
		anagrafica.setGenere(form.getGenere());
		Docente docente = new Docente();
		docente.setAnagrafica(anagrafica);
		return docente;
		
	}
	
}
