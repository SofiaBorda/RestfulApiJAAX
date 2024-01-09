package com.ajaax.restfulapi.repository;

import static org.junit.jupiter.api.Assertions.*;

import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;

import com.ajaax.restfulapi.model.Local;

@DataJpaTest
class LocalRepositoryTest {
	
	@Autowired
	LocalRepository localRepository;
	
	@Autowired
	TestEntityManager testEntityManager; 

	@BeforeEach
	void setUp() throws Exception {
		Local local = 
				Local.builder()
					.name("Supermarket")
					.floor("Third floor")
					.code("Sup-010")
					.build();
		testEntityManager.persist(local);
	}
	@Test
	public void findLocalByNameIgnoreCaseFound() {
		Optional<Local> local = localRepository.findByNameIgnoreCase("Supermarket");
		assertEquals(local.get().getName(), "Supermarket");
	}
	@Test
	public void findLocalByNameIgnoreCaseNotFound() {
		Optional<Local> local = localRepository.findByNameIgnoreCase("Cinema");
		assertEquals(local, Optional.empty());
	}
	
	

}
