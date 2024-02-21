package com.ferraro.uni.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ferraro.uni.entity.Docente;

public interface DocenteRepository extends JpaRepository<Docente, Long>{
	
	boolean existsByCodiceDocente(String codiceDocente);
	
	boolean existsByAnagrafica_Cf(String cf);
	
	boolean existsByAnagrafica_CfAndCdlNotNull(String cf);

	Optional<Docente> findByAnagrafica_Cf(String cf);
}
