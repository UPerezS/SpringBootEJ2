package com.mx.sda.controllers;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.mx.sda.dto.GuardarUsuario;
import com.mx.sda.dto.UsuarioRegistrado;
import com.mx.sda.service.auth.LoginService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/usuarios")
public class UsuarioController {
	
	@Autowired
	private LoginService loginService;
	
	@PostMapping
	public ResponseEntity<?> createOne(@RequestBody @Valid GuardarUsuario nuevoUsuario, BindingResult result){
		if(result.hasFieldErrors()) {
			return validation(result);
		}
		UsuarioRegistrado registeredUser = loginService.registerOneUsuario(nuevoUsuario);
		return ResponseEntity.status(HttpStatus.CREATED).body(registeredUser);
	}
	
	private ResponseEntity<?> validation(BindingResult result){
		Map<String, String> errors = new HashMap<String, String>();
		result.getFieldErrors().forEach(err -> {
			errors.put(err.getField(), "El campo " + err.getField() + " " + err.getDefaultMessage());
		});
		return ResponseEntity.badRequest().body(errors);
	}

}
