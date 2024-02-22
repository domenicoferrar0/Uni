package com.ferraro.uni.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.ferraro.uni.dto.CdlDTO;
import com.ferraro.uni.dto.CdlDTOFull;
import com.ferraro.uni.entity.Cdl;
import com.ferraro.uni.service.CdlService;
import com.ferraro.uni.service.UtilityService;

import jakarta.validation.Valid;

@RestController
@RequestMapping(value = "/rest/api/v0")
public class CDLController {

	@Autowired
	private CdlService service;

	@Autowired
	private UtilityService uService;

	@GetMapping(value = "/get-cdl")
	public ResponseEntity<List<CdlDTOFull>> getAllCdls() {
		List<CdlDTOFull> cdls = service.findAllFull();
	
		return ResponseEntity.ok(cdls);
	}
	
	@GetMapping(value = "/get-cdl-menu")
	public ResponseEntity<List<String>> getForMenu() {
		// STO SEMPLICEMENTE RITORNANDO I NOMI DEL CDL, PER IL SELECT DI STUDENTE
		List<String> cdls = service.findForMenu();
		
		return ResponseEntity.ok(cdls);
	}

	@GetMapping(value = "/get-cdl-nome")
	public ResponseEntity<CdlDTO> getByNome(@RequestParam(value = "nome") String nome) {
		// 404 SE NON LO TROVA
		CdlDTO cdl = service.findByNome(nome);
		
		return ResponseEntity.ok(cdl);
	}
	
	@PostMapping(value = "/save-cdl")
	public ResponseEntity<?> saveCdl(@RequestBody @Valid CdlDTO cdlDTO, BindingResult bRes) {
		if (cdlDTO == null) {
			return ResponseEntity.unprocessableEntity().body("Il form non può essere nullo");
		}
		if (bRes.hasErrors()) {
			return ResponseEntity.unprocessableEntity().body(uService.getFieldErrors(bRes));
		}

		// CHECK NOME UNIQUE
		if(service.existsByNome(cdlDTO.getNome())) {
			return ResponseEntity.status(HttpStatus.CONFLICT).body("Il nome non può essere duplicato");
		}
		Cdl newCdl = service.dtoToCdl(cdlDTO);

		try {
			service.save(newCdl);
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
					.body("Si è verificato un errore con il server, riprova più tardi");
		}
	 	return ResponseEntity.status(HttpStatus.CREATED).body(service.cdlToDTo(newCdl));
	}

}
