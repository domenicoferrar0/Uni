package com.ferraro.uni.service;

import java.util.List;

import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ferraro.uni.dto.CdlDTO;
import com.ferraro.uni.dto.CdlDTOFull;
import com.ferraro.uni.entity.Cdl;
import com.ferraro.uni.exceptions.CdlNotFoundException;
import com.ferraro.uni.mapper.CdlMapper;
import com.ferraro.uni.repository.CdlRepository;

import jakarta.transaction.Transactional;

@Service
public class CdlService {

	@Autowired
	CdlRepository cdlRep;
	
	@Autowired
	CdlMapper cdlMapper;
	
	public Cdl dtoToCdl(CdlDTO cdlDTO) {
		return cdlMapper.dtoToCdl(cdlDTO);
	}
		
	@Transactional
	public CdlDTO save(Cdl cdl) {
		return cdlMapper.cdlToDto(cdlRep.save(cdl));
	}
	
	public List<CdlDTO> findAll(){
		List<Cdl> cdls = cdlRep.findAll();
		return cdlMapper.cdlToDtos(cdls);
	}
	
	public CdlDTO findByNome(String nome) {
		return cdlRep.findByNome(nome).map(cdlMapper::cdlToDto).orElseThrow(()-> new CdlNotFoundException(nome));
	}
	
	public Cdl findEntityByNome(String nome) {
		Optional<Cdl> opt = cdlRep.findByNome(nome);
		if(opt.isPresent()) {
			return opt.get();
		}
		return null;
	}
	
	public boolean existsByNome(String nome) {
		return cdlRep.existsByNome(nome);
		
	}
	
	public List<CdlDTOFull> findAllFull(){
		List<Cdl> cdls = cdlRep.findAll();
		return cdlMapper.cdlsToDtoFull(cdls);
	}
	
	public Optional<Cdl> findById(Long id) {
		return cdlRep.findById(id);
	}
	
	public List<String> findForMenu(){
		return cdlRep.findNome();
	}

	public CdlDTO cdlToDTo(Cdl newCdl) {
		
		return cdlMapper.cdlToDto(newCdl);
	}
	
}
