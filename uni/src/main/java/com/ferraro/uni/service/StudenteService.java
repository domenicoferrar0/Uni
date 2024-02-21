package com.ferraro.uni.service;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ferraro.uni.dto.Form;
import com.ferraro.uni.dto.StudenteDTO;
import com.ferraro.uni.entity.Cdl;
import com.ferraro.uni.entity.Studente;
import com.ferraro.uni.exceptions.PersonNotFoundException;
import com.ferraro.uni.mapper.StudentMapper;
import com.ferraro.uni.repository.StudenteRepository;

import jakarta.transaction.Transactional;

@Service
public class StudenteService {

	@Autowired
	StudenteRepository sRep;

	@Autowired
	StudentMapper mapper;
	
	@Transactional
	public Studente save(Studente studente) {
		Studente nuovoStudente = sRep.save(studente);
		return nuovoStudente;
	}

	public List<StudenteDTO> findAll() {
		List<StudenteDTO> studentiDTO = mapper.studentsToDto(sRep.findAll());
		return studentiDTO;
	}

	public boolean isUnique(Studente studente) {
		String matricola = studente.getMatricola();
		String cf = studente.getAnagrafica().getCf();
		if (matricola != null && sRep.existsByMatricola(matricola)) {
			return false;
			}
		if (sRep.existsByAnagrafica_Cf(cf)) {
			return false;
		}
				
		return true;
	}

	public StudenteDTO findByCf(String cf) {
		return sRep.findByAnagrafica_Cf(cf).map(mapper::studentToDTO).orElseThrow(() -> new PersonNotFoundException(cf) );
	}

	public Studente findEntityByCf(String cf) {
		return sRep.findByAnagrafica_Cf(cf).orElseThrow(() -> new PersonNotFoundException(cf));
	}

	public boolean hasCdl(String cf) {
		if (sRep.existsByAnagrafica_CfAndCdlNotNull(cf)) {
			return true;
		}
		return false;
	}

	public Studente formToStudente(Form form) {
		Studente studente = mapper.formToStudente(form);
		return studente;

	}

	public Integer countByCdl(Cdl cdl) {
		return sRep.countByCdl(cdl);
	}

	public StudenteDTO studenteToDto(Studente studente) {
		return mapper.studentToDTO(studente);
	}

}
