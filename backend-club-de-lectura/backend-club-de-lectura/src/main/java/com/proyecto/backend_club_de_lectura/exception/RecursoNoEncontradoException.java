package com.proyecto.backend_club_de_lectura.exception;

public class RecursoNoEncontradoException extends RuntimeException {
	public RecursoNoEncontradoException(String mensaje){
		super(mensaje);
	}
}