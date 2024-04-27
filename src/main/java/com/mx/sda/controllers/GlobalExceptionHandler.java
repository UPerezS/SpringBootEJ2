package com.mx.sda.controllers;

import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.HashMap;
import java.util.Map;

@RestControllerAdvice
public class GlobalExceptionHandler {
	@ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<Object> handleValidationExceptions(MethodArgumentNotValidException ex) {
        // Obtener los errores de validación del BindingResult
        BindingResult bindingResult = ex.getBindingResult();
        
        // Crear un mapa para almacenar los errores
        Map<String, String> validationErrors = new HashMap<>();
        
        // Iterar sobre los errores de campo y agregarlos al mapa
        for (FieldError fieldError : bindingResult.getFieldErrors()) {
            validationErrors.put(fieldError.getField(), fieldError.getDefaultMessage());
        }
        
        // Devolver una respuesta con los errores de validación y el estado HTTP 400 Bad Request
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(validationErrors);
    }
}
