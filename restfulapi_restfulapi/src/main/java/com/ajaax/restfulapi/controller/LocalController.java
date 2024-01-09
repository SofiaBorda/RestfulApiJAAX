package com.ajaax.restfulapi.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.ajaax.restfulapi.error.LocalNotFoundExcepcion;
import com.ajaax.restfulapi.model.Local;
import com.ajaax.restfulapi.service.LocalService;

import jakarta.validation.Valid;

@RestController
public class LocalController {
	
	@Autowired
	LocalService data;
	
	@GetMapping("/findAllLocals")
	public List<Local> findAllLocals(){
		return data.findAllLocals();
	}
	@PostMapping("/saveLocal")
	public Local saveLocal(@Valid @RequestBody Local local) {
		return data.saveLocal(local);
	}
	@PutMapping("/updateLocal/{id}")
	public Local updateLocal(@PathVariable Long id,@RequestBody Local local) {
		return data.updateLocal(id, local);
	}
	@DeleteMapping("/deleteLocal/{id}")
	public String deleteLocal(@PathVariable Long id) {
		data.deleteLocal(id);
		return "Succesfully deleted";
	}
	@GetMapping("/findLocalByNameWithJPQL/{name}")
	Optional<Local> findLocalByNameWithJPQL(@PathVariable String name){
		return data.findLocalByNameWithJPQL(name);
	}
	@GetMapping("/findByName/{name}")
	Optional<Local> findByName(@PathVariable String name) {
		return data.findByName(name);
	}
	@GetMapping("/findByNameIgnoreCase/{name}")
	Optional<Local> findByNameIgnoreCase(@PathVariable String name) {
		return data.findByNameIgnoreCase(name);
	}
	@GetMapping("/findLocalById/{id}")
	Local findLocalById(@PathVariable Long id) throws LocalNotFoundExcepcion {
		return data.findLocalById(id);
	}
}
