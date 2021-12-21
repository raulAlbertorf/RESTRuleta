package com.ibm.academia.apirest.models.services;

import java.util.List;
import java.util.Optional;

import com.ibm.academia.apirest.models.entities.Ruleta;
import com.ibm.academia.apirest.models.enums.Estatus;

public interface IRuletaService  {

	public List<Ruleta> buscarTodas();
	public Optional<Ruleta> buscarPorId(Long id);
	public Ruleta guardar(Ruleta ruleta);
	public Ruleta actualizar(Ruleta ruletaEncontrada, Estatus estatus);
}
