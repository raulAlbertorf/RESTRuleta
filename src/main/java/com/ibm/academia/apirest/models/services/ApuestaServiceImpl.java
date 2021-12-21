package com.ibm.academia.apirest.models.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ibm.academia.apirest.models.entities.Apuesta;
import com.ibm.academia.apirest.models.entities.Ruleta;
import com.ibm.academia.apirest.models.enums.Estatus;
import com.ibm.academia.apirest.models.exceptions.BadRequestException;
import com.ibm.academia.apirest.models.exceptions.NotFoundException;
import com.ibm.academia.apirest.models.repositories.IApuestaRepository;

@Service
public class ApuestaServiceImpl implements IApuestaService {

	@Autowired
	IApuestaRepository apuestaRepository;

	@Autowired
	IRuletaService ruletaService;

	@Override
	public Apuesta guardar(String alias, Integer apuesta, Integer numero, String color, Integer ruletaId) {
		
		Optional<Ruleta> oRuleta = ruletaService.buscarPorId(Long.valueOf(ruletaId));
		if (oRuleta.get().getEstatus().equals(Estatus.ABIERTA) && (oRuleta.get().getTablero().get(numero).equals(color) || numero==0)) {
			Apuesta apuestaObj = new Apuesta(alias, apuesta, numero, color, oRuleta.get());
			return apuestaRepository.save(apuestaObj);
		} else {
			throw new BadRequestException(
					String.format("Ruleta ID: %d no se encuentra habilitada para recibir apuestas", ruletaId));
		}
	}

	@Override
	public Optional<Apuesta> buscarPorId(Long apuestaId) {
		return apuestaRepository.findById(apuestaId);
	}

	@Override
	public List<Apuesta> buscarTodas() {
		return (List<Apuesta>) apuestaRepository.findAll();
	}

	@Override
	public List<Apuesta> findByRuletaId(Long ruletaId) {
		return apuestaRepository.findByRuletaId(ruletaId);
	}

}
