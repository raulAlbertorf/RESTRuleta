package com.ibm.academia.apirest.controllers;

import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.ibm.academia.apirest.models.entities.Ruleta;
import com.ibm.academia.apirest.models.enums.Estatus;
import com.ibm.academia.apirest.models.services.IApuestaService;
import com.ibm.academia.apirest.models.services.IRuletaService;

@RestController
@RequestMapping("/ruleta")
public class RuletaController {

	@Autowired
	IRuletaService ruletaService;
	
	@Autowired
	IApuestaService apuestaService;
	
	@GetMapping("/listar")
	public ResponseEntity<?> listarTodas(){
		
		return new ResponseEntity<List<Ruleta>>(ruletaService.buscarTodas(), HttpStatus.OK);
	}
	
	@PostMapping("/nueva")
	public ResponseEntity<?> crearRuleta(){
		Ruleta nuevaRuleta = ruletaService.guardar(new Ruleta());
		return new ResponseEntity<Long>(nuevaRuleta.getId(), HttpStatus.CREATED);
	}
	
	@PutMapping("/actualizar/{ruletaId}")
	public ResponseEntity<?> abrirApuestas(@PathVariable Long ruletaId,@RequestParam Estatus estatus){
		Optional<Ruleta> oRuletaEncontrada = ruletaService.buscarPorId(ruletaId);
		if(!oRuletaEncontrada.get().getEstatus().equals(estatus)) {
			Ruleta ruletaActualizada = ruletaService.actualizar(oRuletaEncontrada.get(), estatus);			
		}
		if(estatus.equals(Estatus.CERRADA)) {
			return ResponseEntity.ok(apuestaService.findByRuletaId(ruletaId));
		}else {
			return ResponseEntity.ok(estatus);
		}
	}
	
	@GetMapping("/detalle/{id}")
	public ResponseEntity<?> buscarPorId(@PathVariable Long id){
		return new ResponseEntity<Ruleta>(ruletaService.buscarPorId(id).get(), HttpStatus.OK);
	}
}
