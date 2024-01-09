package com.ajaax.restfulapi.service;

import static org.junit.jupiter.api.Assertions.*;

import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import com.ajaax.restfulapi.model.Local;
import com.ajaax.restfulapi.repository.LocalRepository;

@SpringBootTest
class LocalServiceTest {
	@Autowired
	private LocalService localService;
	@MockBean
	private LocalRepository localRepository;

	@BeforeEach
	void setUp() throws Exception {
		Local local = Local.builder()
				.id(1L)
				.name("Petshop")
				.floor("Second Floor")
				.code("Pet 050")
				.build();
		Mockito.when(localRepository.findByNameIgnoreCase("Petshop")).thenReturn(Optional.of(local));
	}

	@Test
	@DisplayName("Prueba de obtencion de informacion de un local enviando un nombre válido")
	public void findByNameIgnoreCaseShouldFound() {
		String localName = "Petshop";
		Local local = localService.findByNameIgnoreCase(localName).get();
		assertEquals(localName, local.getName());
		System.out.println("local = " + local);
	}

}
