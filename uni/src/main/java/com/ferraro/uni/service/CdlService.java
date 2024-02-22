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
	private CdlRepository cdlRepository;
	
	@Autowired
	private CdlMapper cdlMapper;

	public Cdl dtoToCdl(CdlDTO cdlDTO) {
		return cdlMapper.dtoToCdl(cdlDTO);
	}
		
	@Transactional
	public CdlDTO save(Cdl cdl) {
		return cdlMapper.cdlToDto(cdlRepository.save(cdl));
	}
	
	public List<CdlDTO> findAll(){
		List<Cdl> cdls = cdlRepository.findAll();
		return cdlMapper.cdlToDtos(cdls);
	}
	
	public CdlDTO findByNome(String nome) {
		return cdlRepository
				.findByNome(nome)
				.map(cdlMapper::cdlToDto)
				.orElseThrow(()-> new CdlNotFoundException(nome));
	}
	
	public Cdl findEntityByNome(String nome) {
		Optional<Cdl> opt = cdlRepository.findByNome(nome);
        return opt.orElse(null);
    }
	
	public boolean existsByNome(String nome) {
		return cdlRepository.existsByNome(nome);
	}
	
	public List<CdlDTOFull> findAllFull(){
		List<Cdl> cdls = cdlRepository.findAll();
		return cdlMapper.cdlsToDtoFull(cdls);
	}
	
	public Optional<Cdl> findById(Long id) {
		return cdlRepository.findById(id);
	}
	
	public List<String> findForMenu(){
		return cdlRepository.findNome();
	}

	public CdlDTO cdlToDTo(Cdl newCdl) {
		
		return cdlMapper.cdlToDto(newCdl);
	}
	
}
