package com.ajaax.restfulapi.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.ajaax.restfulapi.model.Local;

public interface LocalRepository extends JpaRepository<Local, Long>{
	//Consulta con JPQL
	@Query("SELECT l FROM Local l WHERE l.name = :name")
	Optional<Local> findLocalByNameWithJPQL(String name);
	//Consulta con invesión de control
	Optional<Local> findByName(String name);
	
	Optional<Local> findByNameIgnoreCase(String name);
	

}
