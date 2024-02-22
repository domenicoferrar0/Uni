package com.ferraro.uni.service;

import java.util.List;

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
	private DocenteRepository docenteRepository;

	@Autowired
	private DocenteMapper docenteMapper;
	
	@Transactional
	public DocenteDTO save(Docente docente) {
		return docenteMapper.docenteToDTO(docenteRepository.save(docente));
	}

	public boolean isUnique(Docente docente) {
		String codiceDocente = docente.getCodiceDocente();
		String cf = docente.getAnagrafica().getCf();
		if (docenteRepository.existsByCodiceDocente(codiceDocente) || docenteRepository.existsByAnagrafica_Cf(cf)) {
			return false;
		}
		return true;
	}

	public List<DocenteDTO> findAll() {
		List<DocenteDTO> docenti = docenteMapper.docentiToDto(docenteRepository.findAll());
		return docenti;
	}

	public boolean hasCdl(String cf) {
		return docenteRepository.existsByAnagrafica_CfAndCdlNotNull(cf);
	}
	public Docente formToDocente(Form form) {		
		return docenteMapper.formToDocente(form);
	}
	
	public Docente findEntityByCf(String cf) {
		return docenteRepository
				.findByAnagrafica_Cf(cf)
				.orElseThrow(() -> new PersonNotFoundException(cf));
	}

	public DocenteDTO docenteToDto(Docente docente) {
		
		return docenteMapper.docenteToDTO(docente);
	}
}