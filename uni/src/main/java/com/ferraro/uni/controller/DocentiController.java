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

import com.ferraro.uni.dto.DocenteDTO;
import com.ferraro.uni.dto.Form;
import com.ferraro.uni.entity.Cdl;
import com.ferraro.uni.entity.Docente;
import com.ferraro.uni.service.CdlService;
import com.ferraro.uni.service.DocenteService;
import com.ferraro.uni.service.UtilityService;

import jakarta.validation.Valid;

@RestController
@RequestMapping(value = "/rest/api/v0")
public class DocentiController {

	@Autowired
	private DocenteService service;

	@Autowired
	private UtilityService uService;

	@Autowired
	private CdlService cdlService;

	@GetMapping(value = "/get-docenti")
	public ResponseEntity<List<DocenteDTO>> getAll() {
		List<DocenteDTO> docenti = service.findAll();
		
		return ResponseEntity.ok(docenti);
	}

	@GetMapping(value = "/docente-checkCDL")
	public ResponseEntity<Boolean> CdlCheck(@RequestParam("cf") String cf) {
		Boolean cdlCheck = service.hasCdl(cf);
		return ResponseEntity.ok(cdlCheck);
	}
	
	@PostMapping(value = "/save-docente")
	public ResponseEntity<?> saveDocente(@RequestBody @Valid Form form, BindingResult bRes) {
		if (form == null) {
			return ResponseEntity.unprocessableEntity().body("Il form non può essere nullo");
		}
		Cdl cdl = null;

		// BINDINGRESULT GESTISCE LA VALIDAZIONE BASANDOSI SULLE ANNOTATION DEI CAMPI
		if (bRes.hasErrors()) {

			return ResponseEntity.unprocessableEntity().body(uService.getFieldErrors(bRes));
		}
		String nomeCdl = form.getNomeCdl();
										
		if (!nomeCdl.isEmpty()) { //GLI ASSEGNA IL CDL SOLO SE INSERITO
			Cdl opt = cdlService.findEntityByNome(nomeCdl);
			if (opt != null) {
				cdl = opt;
			} else {

				return ResponseEntity.status(HttpStatus.NOT_FOUND)
						.body(uService.fieldError("cdl", "Il corso di laurea scelto non esiste"));
			}
		}
		String nuovoCodice = uService.generaCodiceDocente(); //GLI ASSEGNA IL CODICE DOCENTE GENERATO
		Docente docente = service.formToDocente(form);
		docente.setCdl(cdl);
		docente.setCodiceDocente(nuovoCodice);
		if (!service.isUnique(docente)) { // FA UN CHECK SUI VALORI UNIQUE PRIMA DELL'INSERT, CF E CODICE DOCENTE
			return ResponseEntity.status(HttpStatus.CONFLICT).body(uService.fieldError("cf",
					"Si è verificato un problema, Codice fiscale o Codice docente già registrati"));
		}
		try {
			service.save(docente);
		} catch (Exception e) {

			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
					.body("Si è verificato un errore con il server, riprova più tardi");
		}

		return ResponseEntity.status(HttpStatus.CREATED).body(service.docenteToDto(docente));
	}
	
	@PostMapping(value = "/docente-selectCDL")
	public ResponseEntity<?> updateDocenteCdl(@RequestParam(value = "cf") String cf,
			@RequestParam(value = "nomeCDL") String nomeCDL) {
		if (cf == null || cf.isBlank() || nomeCDL == null || nomeCDL.isBlank()) {
			return ResponseEntity.unprocessableEntity().body("Inserisci i valori necessari");
		}
		Docente docente = service.findEntityByCf(cf); //Viene gestito da exception handler se non lo trova
		
		if (docente.getCdl() != null) { //NON PUO' ESSERE ASSEGNATO AD UN CDL SE NE HA GIà UNO
			return ResponseEntity.status(HttpStatus.CONFLICT).body("Il Docente è già assegnato ad un CDL :" + cf);
		}

		Cdl cdl = cdlService.findEntityByNome(nomeCDL);
		if (cdl == null) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Corso di laurea non trovato " + nomeCDL);
		}
		docente.setCdl(cdl);
				
		try {
			service.save(docente);
		} catch (Exception e) {
			e.printStackTrace();
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
					.body("Si è verificato un errore con il server, riprova più tardi");
		}
		return ResponseEntity.ok(service.docenteToDto(docente));
	}
	
	@PostMapping(value = "/docente-removeCDL")
	public ResponseEntity<?> removeCdl(@RequestParam(value = "cf") String cf,
			@RequestParam(value = "nomeCDL") String nomeCDL) {
		if (cf == null || cf.isBlank() || nomeCDL == null || nomeCDL.isBlank()) {
			return ResponseEntity.unprocessableEntity().body("Inserisci i valori necessari");
		}
		Docente docente  = service.findEntityByCf(cf);
		
		if (docente.getCdl() == null) {
			return ResponseEntity.unprocessableEntity().body("Docente non assegnato ad un corso di laurea :" + cf);
		}
		Cdl cdl = cdlService.findEntityByNome(nomeCDL);
		if (cdl == null) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Corso di laurea non trovato " + nomeCDL);
		}

		if (docente.getCdl() != cdl) { //SE NON C'E CORRISPONDENZA TRA DOCENTE SELEZIONATO E IL CDL DA RIMUOVERE
			return ResponseEntity.status(HttpStatus.CONFLICT)
					.body("Il docente :" + cf + ", non è assegnato a questo corso di laurea " + nomeCDL);
		}
		docente.setCdl(null);

		try {
			service.save(docente);
		} catch (Exception e) {
			e.printStackTrace();
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
					.body("Si è verificato un errore con il server, riprova più tardi");
			
		}
		return ResponseEntity.ok("Docente rimosso correttamente dal Corso di Laurea: "+cf);

	}
	
	
	
}
