package com.ibm.academia.apirest.models.services;

import java.util.List;
import java.util.Optional;

import org.springframework.web.bind.annotation.RequestParam;

import com.ibm.academia.apirest.models.entities.Apuesta;

public interface IApuestaService {
	public Apuesta guardar(String alias,Integer apuesta,Integer numero,String color,Integer ruletaId);
	public Optional<Apuesta> buscarPorId(Long apuestaId);
	public List<Apuesta> buscarTodas();
	public List<Apuesta> findByRuletaId(Long ruletaId);
}
