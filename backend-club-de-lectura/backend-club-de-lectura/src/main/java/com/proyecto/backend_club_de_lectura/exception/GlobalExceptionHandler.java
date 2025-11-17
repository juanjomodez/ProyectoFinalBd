package com.proyecto.backend_club_de_lectura.exception;

import java.util.HashMap;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler{
    @ExceptionHandler(RecursoNoEncontradoException.class)
    public ResponseEntity<Map<String, String>> manejarRecursoNoEncontrado(RecursoNoEncontradoException ex) {
        Map<String, String> errorResponse = new HashMap<>();
        errorResponse.put("Error!", ex.getMessage());
        return new ResponseEntity<>(errorResponse, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(ErrorLogicoException.class)
    public ResponseEntity<Map<String, String>> manejarErrorLogico(ErrorLogicoException ex) {
        Map<String, String> errorResponse = new HashMap<>();
        errorResponse.put("error", ex.getMessage());
        return new ResponseEntity<>(errorResponse, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<Map<String, String>> manejarErroresGenerales(Exception ex) {
        Map<String, String> errorResponse = new HashMap<>();
        errorResponse.put("Error!", "Ocurrió un fallo inesperado. Por favor, intente más tarde.");
        return new ResponseEntity<>(errorResponse, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @ExceptionHandler(RetoConInscritosException.class)
    public ResponseEntity<Map<String, String>> manejarRetoConInscritos(RetoConInscritosException ex) {
        Map<String, String> errorResponse = new HashMap<>();
        errorResponse.put("Error!", ex.getMessage());
        return new ResponseEntity<>(errorResponse, HttpStatus.BAD_REQUEST);
    }
    
}
