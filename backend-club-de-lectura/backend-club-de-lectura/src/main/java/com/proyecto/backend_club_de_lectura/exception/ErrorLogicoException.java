package com.proyecto.backend_club_de_lectura.exception;

public class ErrorLogicoException extends RuntimeException {
    public ErrorLogicoException(String mensaje) {
        super(mensaje);
    }
}