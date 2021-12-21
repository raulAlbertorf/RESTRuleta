package com.ibm.academia.apirest.models.repositories;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import com.ibm.academia.apirest.models.entities.Apuesta;

public interface IApuestaRepository extends CrudRepository<Apuesta, Long> {

	public List<Apuesta> findByRuletaId(Long ruletaId);
}
