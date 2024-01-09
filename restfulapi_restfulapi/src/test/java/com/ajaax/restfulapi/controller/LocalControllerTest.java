package com.ajaax.restfulapi.controller;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import com.ajaax.restfulapi.model.Local;
import com.ajaax.restfulapi.service.LocalService;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;

@WebMvcTest(LocalController.class)
class LocalControllerTest {
	@Autowired
	private MockMvc mockMvc;
	
	@MockBean
	private LocalService localService;
	
	private Local local;

	@BeforeEach
	void setUp() throws Exception {
		local = Local.builder()
				.id(1L)
				.name("Cinema")
				.floor("Second Floor")
				.code("Cine 040")
				.build();
				
	}

	@Test
	public void saveLocal() throws Exception{
		Local PostLocal = Local.builder()
				.name("Cinema")
				.floor("Second Floor")
				.code("Cine 040")
				.build();
		Mockito.when(localService.saveLocal(PostLocal)).thenReturn(local);
		mockMvc.perform(post("/saveLocal").contentType(MediaType.APPLICATION_JSON)
                .content("{\n" +
                        "    \"name\":\"Cinema\",\n" +
                        "    \"floor\": \"Fourth Floor\",\n" +
                        "    \"code\":\"Cin-040-4\"\n" +
                        "}"))
                .andExpect(status().isOk());
				
	}
	@Test
	public void findLocalById() throws Exception{
		Mockito.when(localService.findLocalById(1L)).thenReturn(local);
		mockMvc.perform(get("/findLocalById/1")
				.contentType(MediaType.APPLICATION_JSON))
				.andExpect(status().isOk())
				.andExpect(jsonPath("$.name").value(local.getName()));
	}

}
