package com.ibm.academia.apirest.models.exceptions.handler;

import java.util.HashMap;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import com.ibm.academia.apirest.models.exceptions.BadRequestException;
import com.ibm.academia.apirest.models.exceptions.NotFoundException;


@ControllerAdvice
public class RESTRuletaException {

	@ExceptionHandler(value = BadRequestException.class)
	public ResponseEntity<Object> formatoInvalidoException(BadRequestException excepcion) {
		Map<String, Object> respuesta = new HashMap<String, Object>();
		respuesta.put("Error", excepcion.getMessage());
		return new ResponseEntity<>(respuesta, HttpStatus.BAD_REQUEST);
	}

	@ExceptionHandler(value = NotFoundException.class)
	public ResponseEntity<Object> noExisteException(NotFoundException excepcion) {
		Map<String, Object> respuesta = new HashMap<String, Object>();
		respuesta.put("Error", excepcion.getMessage());
		return new ResponseEntity<>(respuesta, HttpStatus.NOT_FOUND);
	}
	
}
