package com.proyecto.backend_club_de_lectura.service;

import java.util.List;

import com.proyecto.backend_club_de_lectura.model.LibroModel;

public interface ILibroService {

    List<LibroModel> listarLibros();

    LibroModel guardarLibro(LibroModel libro);

    LibroModel obtenerLibroPorId(int id);

    void eliminarLibro(int id);
}
