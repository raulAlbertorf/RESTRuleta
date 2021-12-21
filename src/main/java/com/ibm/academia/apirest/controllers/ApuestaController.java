package com.ibm.academia.apirest.controllers;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.ibm.academia.apirest.models.entities.Apuesta;
import com.ibm.academia.apirest.models.services.IApuestaService;

import io.swagger.v3.oas.annotations.parameters.RequestBody;

@RestController
@RequestMapping("/apuesta")
public class ApuestaController {

	@Autowired
	IApuestaService apuestaService;

	@PostMapping("/nueva")
	public ResponseEntity<?> nuevaApuesta(@RequestParam String alias, @RequestParam Integer apuesta,
			@RequestParam Integer numero, @RequestParam String color, @RequestParam Integer ruletaId) {
		Apuesta apuestaGuardada = apuestaService.guardar(alias, apuesta, numero, color, ruletaId);
		return new ResponseEntity<Apuesta>(apuestaGuardada, HttpStatus.CREATED);
	}

	@GetMapping("/listar")
	public ResponseEntity<?> buscarTodas() {
		List<Apuesta> apuestas = apuestaService.buscarTodas();
		return new ResponseEntity<List<Apuesta>>(apuestas, HttpStatus.OK);
	}

	@GetMapping("/{apuestaId}")
	public ResponseEntity<?> detalleApuesta(@PathVariable Long apuestaId) {

		Optional<Apuesta> oApuesta = apuestaService.buscarPorId(apuestaId);
		return new ResponseEntity<Apuesta>(oApuesta.get(), HttpStatus.OK);

	}

}
