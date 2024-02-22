package com.ferraro.uni.controller;

import java.util.List;

import org.apache.commons.lang3.StringUtils;
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

import com.ferraro.uni.dto.Form;
import com.ferraro.uni.dto.StudenteDTO;
import com.ferraro.uni.entity.Cdl;
import com.ferraro.uni.entity.Studente;
import com.ferraro.uni.service.CdlService;
import com.ferraro.uni.service.StudenteService;
import com.ferraro.uni.service.UtilityService;

import jakarta.validation.Valid;

@RestController
@RequestMapping(value = "/rest/api/v0")
public class StudentiController {

	@Autowired
	private StudenteService service;

	@Autowired
	private CdlService cdlService;

	@Autowired
	private UtilityService uService;

	@GetMapping(value = "/get-studenti")
	public ResponseEntity<List<StudenteDTO>> getStudenti() {
		List<StudenteDTO> studenti = service.findAll();
		
		return ResponseEntity.ok(studenti);
	}

	@GetMapping(value = "/get-studente-CF")
	public ResponseEntity<StudenteDTO> getByCf(@RequestParam("cf") String cf) {
		
		StudenteDTO studente = service.findByCf(cf);
		
		return ResponseEntity.ok(studente); //404 se non lo trova
	}

	@GetMapping(value = "/studente-checkCDL")
	public ResponseEntity<Boolean> CdlCheck(@RequestParam("cf") String cf) {
		Boolean cdlCheck = service.hasCdl(cf);
		return ResponseEntity.ok(cdlCheck);
	}

	@PostMapping(value = "/save-studente")
	public ResponseEntity<?> saveStudente(@RequestBody @Valid Form form, BindingResult bRes) {
		
		if (form == null) {
			return ResponseEntity.unprocessableEntity().body("il form non può essere nullo");
		}
		
		// MATRICOLA E CDL PLACEHOLDER
		Cdl cdl = null;
		String matricola = null;

		// BINDINGRESULT GESTISCE LA VALIDAZIONE BASANDOSI SULLE ANNOTATION DEI CAMPI
		if (bRes.hasErrors()) {
			//GLI ERRORI DI BINDING RESULT VENGONO MANDATI AL FRONT END SOTTOFORMA DI MAP(field, errorMessage)
			return ResponseEntity.unprocessableEntity().body(uService.getFieldErrors(bRes)); 
		}
		// NOME CDL CHE VIENE DAL FORM
		String nomeCdl = form.getNomeCdl();

		if (!nomeCdl.isEmpty()) { // PROVA AD ASSEGNARE SOLO SE IL CAMPO NON E' VUOTO
			// CERCA LA CDL IN BASE AL NOME
			Cdl opt = cdlService.findEntityByNome(nomeCdl);

			if (opt == null ) {
				return ResponseEntity.unprocessableEntity()
						.body(uService.fieldError("cdl", "Il corso di laurea scelto non esiste"));
			}

			// CONTROLLA CHE IL NUMERO ATTUALE DI STUDENTI IN UN CDL NON SUPERI IL NUMERO MASSIMO
			cdl = opt;
			System.out.println("\n \n \n \n CONTROLLO NUMERO \n \n \n \n");
			Integer studentiAttuali = service.countByCdl(cdl);

			if (studentiAttuali >= cdl.getMaxStudenti()) {
				return ResponseEntity.status(HttpStatus.CONFLICT)
						.body(uService.fieldError("cdl", "Il corso di laurea è attualmente pieno"));
			}
			matricola = uService.generaMatricola(cdl.getId()); // VIENE ASSEGNATA MATRICOLA SOLO SE SCELTO CDL

		}
		// VIENE CREATO ANAGRAFICA E ASSOCIATA ALLO STUDENTE SULLA BASE DEL FORM
		Studente studente = service.formToStudente(form);
		studente.setCdl(cdl); // VENGONO ASSEGNATI, RIMANGONO NULL SE NON VIENE INSERITO CDL NEL FORM
		studente.setMatricola(matricola);
		System.out.println("\n \n \n \n CONTROLLO UNIQUE \n \n \n \n");
		if (!service.isUnique(studente)) { // FA UN CHECK SUI VALORI UNIQUE PRIMA DELL'INSERT, CF E MATRICOLA
			return ResponseEntity.status(HttpStatus.CONFLICT).body(uService.fieldError("cf",
					"Si è verificato un problema, Codice fiscale o Matricola già registrati"));
		}
		
		try {
			service.save(studente);
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("\n \n \n \n MORTO AL SALVATAGGIO \n \n \n \n");

			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
					.body("Si è verificato un errore con il server, riprova più tardi");
		}

		return ResponseEntity.status(HttpStatus.CREATED).body(service.studenteToDto(studente));
	}

	@PostMapping(value = "/studente-selectCDL")
	public ResponseEntity<?> updateStudenteCdl(@RequestParam(value = "cf") String cf,
			@RequestParam(value = "nomeCDL") String nomeCDL) {
		if (cf == null || cf.isEmpty() || nomeCDL == null || nomeCDL.isEmpty()) {
			return ResponseEntity.unprocessableEntity().body("Inserisci i valori necessari");
		}
		Studente studente = service.findEntityByCf(cf); // CERCA STUDENTE, 404 SE NON C'è
		
		if (studente.getCdl() != null) { // NON PUO' ISCRIVERSI AD UN CDL SE E' GIà ISCRITTO AD UN ALTRO
			return ResponseEntity.status(HttpStatus.CONFLICT).body("Studente già iscritto ad un corso di laurea :" + cf);
		}

		Cdl cdl = cdlService.findEntityByNome(nomeCDL);
		if (cdl == null) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Corso di laurea non trovato: "+nomeCDL);
		}
		studente.setCdl(cdl);
		String matricola = uService.generaMatricola(cdl.getId()); // GENERA MATRICOLA SULLA BASE DELL'ID CDL
		studente.setMatricola(matricola);

		if (service.countByCdl(cdl) >= cdl.getMaxStudenti()) { // CONTROLLA SE IL CDL NON E' PIENO
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Il corso di laurea scelto è attualmente pieno " + nomeCDL);
		}

		try {
			service.save(studente);
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
					.body("Si è verificato un errore con il server, riprova più tardi");
		}
		return ResponseEntity.ok("Studente iscritto correttamente " + nomeCDL);
	}

	@PostMapping(value = "/studente-removeCDL")
	public ResponseEntity<?> removeCdl(@RequestParam(value = "cf") String cf,
			@RequestParam(value = "nomeCDL") String nomeCDL) {
		if (StringUtils.isBlank(cf) || StringUtils.isBlank(nomeCDL)) {
			return ResponseEntity.unprocessableEntity().body("Inserisci i valori necessari");
		}
		Studente studente = service.findEntityByCf(cf); // 404 SE NON C'E'
		
		if (studente.getCdl() == null) { // NON PUOI RIMUOVERE UN CDL CHE NON ESISTE
			return ResponseEntity.unprocessableEntity().body("Studente non iscritto ad un corso di laurea :" + cf);
		}
		Cdl cdl = cdlService.findEntityByNome(nomeCDL);
		if (cdl == null) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Corso di laurea non trovato " + nomeCDL);
		}

		if (studente.getCdl() != cdl) { // SE LO STUDENTE NON E' ISCRITTO AL CDL TROVATO
			return ResponseEntity.status(HttpStatus.CONFLICT)
					.body("Lo studente :" + cf + ", non è iscritto a questo corso di laurea " + nomeCDL);
		}
		studente.setCdl(null);

		try {
			service.save(studente);
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
					.body("Si è verificato un errore con il server, riprova più tardi");
		}
		return ResponseEntity.ok("Studente disiscritto correttamente: " + cf);

	}

}
