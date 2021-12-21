package com.ibm.academia.apirest.models.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ibm.academia.apirest.models.entities.Ruleta;
import com.ibm.academia.apirest.models.enums.Estatus;
import com.ibm.academia.apirest.models.exceptions.NotFoundException;
import com.ibm.academia.apirest.models.repositories.IRuletaRepository;

@Service
public class RuletaServiceImpl implements IRuletaService {
	
	@Autowired 
	IRuletaRepository repository;

	@Override
	@Transactional
	public Ruleta guardar(Ruleta ruleta) {
		return repository.save(ruleta);
	}

	@Override
	@Transactional(readOnly = true)
	public List<Ruleta> buscarTodas() {
		return (List<Ruleta>) repository.findAll();
	}

	@Override
	@Transactional
	public Ruleta actualizar(Ruleta ruletaEncontrada, Estatus estatus) {
		
		ruletaEncontrada.setEstatus(estatus);
		return repository.save(ruletaEncontrada);
	}

	@Override
	@Transactional(readOnly = true)
	public Optional<Ruleta> buscarPorId(Long id) {
		Optional<Ruleta> ruleta = repository.findById(id);
		if(!ruleta.isPresent()) {
			throw new NotFoundException(String.format("No se encontro ruleta con ID: %d", id));
		}
		return ruleta;
	}
	
	
}
