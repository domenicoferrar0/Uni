package com.ferraro.uni.mapper;

import java.util.List;
import java.util.stream.Collectors;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

import com.ferraro.uni.dto.Form;
import com.ferraro.uni.dto.StudenteDTO;
import com.ferraro.uni.entity.Anagrafica;
import com.ferraro.uni.entity.Studente;


@Mapper(componentModel = "spring")
public interface StudentMapper {
	
	StudentMapper INSTANCE = Mappers.getMapper(StudentMapper.class);
	
	@Mapping(source = "studente.anagrafica", target = "anagraficaDTO")
	@Mapping(source = "studente.cdl", target = "cdlDTO")
	public StudenteDTO studentToDTO(Studente studente);
	
	default List<StudenteDTO> studentsToDto(List<Studente> studenti){
		return studenti.stream().map(this::studentToDTO).collect(Collectors.toList());
	}
	
	default Studente formToStudente(Form form) {
		Anagrafica anagrafica = new Anagrafica();
		anagrafica.setLuogoDiNascita(form.getLuogoDiNascita());
		anagrafica.setNome(form.getNome());
		anagrafica.setCognome(form.getCognome());
		anagrafica.setNascita(form.getNascita());
		anagrafica.setIndirizzo(form.getIndirizzo());
		anagrafica.setCf(form.getCf());
		anagrafica.setGenere(form.getGenere());
		Studente studente = new Studente();
		studente.setAnagrafica(anagrafica);
		
		return studente;
		
	}
}
