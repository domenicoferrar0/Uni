package com.ferraro.uni.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;


import com.ferraro.uni.entity.Cdl;

public interface CdlRepository extends JpaRepository<Cdl, Long>{
	
	boolean existsByNome(String nome);
	
	Optional<Cdl> findByNome(String nome);
	
	
	@Query("Select nome from Cdl")
	List<String> findNome();
	
	
}
