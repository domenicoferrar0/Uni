package com.ferraro.uni.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ferraro.uni.dto.DocenteDTO;
import com.ferraro.uni.dto.Form;
import com.ferraro.uni.entity.Docente;
import com.ferraro.uni.mapper.DocenteMapper;
import com.ferraro.uni.repository.DocenteRepository;
import com.ferraro.uni.exceptions.PersonNotFoundException;

import jakarta.transaction.Transactional;

@Service
public class DocenteService {

	@Autowired
	DocenteRepository dRep;

	@Autowired
	DocenteMapper dMapper;
	
	@Transactional
	public DocenteDTO save(Docente docente) {
		return dMapper.docenteToDTO(dRep.save(docente));
	}

	public boolean isUnique(Docente docente) {
		String codiceDocente = docente.getCodiceDocente();
		String cf = docente.getAnagrafica().getCf();
		if (dRep.existsByCodiceDocente(codiceDocente) || dRep.existsByAnagrafica_Cf(cf)) {
			return false;
		}
		return true;
	}

	public List<DocenteDTO> findAll() {
		List<DocenteDTO> docenti = dMapper.docentiToDto(dRep.findAll());
		return docenti;
	}

	public boolean hasCdl(String cf) {
		if(dRep.existsByAnagrafica_CfAndCdlNotNull(cf)) {
			return true;
		}
		return false;
	}
	public Docente formToDocente(Form form) {		
		Docente docente = dMapper.formToDocente(form);
		return docente;
	}
	
	public Docente findEntityByCf(String cf) {
		return dRep.findByAnagrafica_Cf(cf).orElseThrow(() -> new PersonNotFoundException(cf));
	}

	public DocenteDTO docenteToDto(Docente docente) {
		
		return dMapper.docenteToDTO(docente);
	}
}