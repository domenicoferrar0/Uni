package com.ferraro.uni.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.ferraro.uni.entity.Anagrafica;

@Repository
public interface AnagraficaRepository extends JpaRepository<Anagrafica, Long>{

	boolean existsByCf(String cf);
}
