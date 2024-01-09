package com.ajaax.restfulapi.service;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ajaax.restfulapi.error.LocalNotFoundExcepcion;
import com.ajaax.restfulapi.model.Local;
import com.ajaax.restfulapi.repository.LocalRepository;

@Service
public class LocalServiceImpl implements LocalService{
	
	@Autowired
	LocalRepository repo;

	@Override
	public List<Local> findAllLocals() {
		return repo.findAll();
	}

	@Override
	public Local saveLocal(Local local) {
		return repo.save(local);
	}

	@Override
	public Local updateLocal(Long id, Local local) {
		Local localDb = repo.findById(id).get();
		if(Objects.nonNull(local.getCode()) && !"".equalsIgnoreCase(local.getCode())) {
			localDb.setCode(local.getCode());
		}
		if(Objects.nonNull(local.getFloor()) && !"".equalsIgnoreCase(local.getFloor())) {
			localDb.setFloor(local.getFloor());
		}
		if(Objects.nonNull(local.getName()) && !"".equalsIgnoreCase(local.getName())) {
			localDb.setName(local.getName());
		}
		return repo.save(localDb);
	}

	@Override
	public void deleteLocal(Long id) {
		repo.deleteById(id);
		
	}

	@Override
	public Optional<Local> findLocalByNameWithJPQL(String name) {
		return repo.findLocalByNameWithJPQL(name);
	}

	@Override
	public Optional<Local> findByName(String name) {
		return repo.findByName(name);
	}

	@Override
	public Optional<Local> findByNameIgnoreCase(String name) {
		return repo.findByNameIgnoreCase(name);
	}

	@Override
	public Local findLocalById(Long id) throws LocalNotFoundExcepcion {
		Optional<Local> local = repo.findById(id);
		if(!local.isPresent()) {
			throw new LocalNotFoundExcepcion("Local is not available");
		}
		return local.get();
	}

}
