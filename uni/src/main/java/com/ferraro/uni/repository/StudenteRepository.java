package com.ferraro.uni.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.ferraro.uni.entity.Cdl;
import com.ferraro.uni.entity.Studente;

@Repository
public interface StudenteRepository extends JpaRepository<Studente, Long> {
	
	boolean existsByMatricola(String matricola);
	
	Optional<Studente> findByAnagrafica_Cf(String cf);
	
	boolean existsByAnagrafica_Cf(String cf);
	
	boolean existsByAnagrafica_CfAndCdlNotNull(String cf);
	
	Integer countByCdl(Cdl cdl);
}
