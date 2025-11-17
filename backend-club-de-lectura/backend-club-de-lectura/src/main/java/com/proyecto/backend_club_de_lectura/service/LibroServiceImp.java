package com.proyecto.backend_club_de_lectura.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.proyecto.backend_club_de_lectura.exception.RecursoNoEncontradoException;
import com.proyecto.backend_club_de_lectura.model.LibroModel;
import com.proyecto.backend_club_de_lectura.repository.ILibroRepository;

@Service
public class LibroServiceImp implements ILibroService {

    @Autowired
    private ILibroRepository libroRepository;

    @Override
    public List<LibroModel> listarLibros() {
        return libroRepository.findAll();
    }

    @Override
    public LibroModel guardarLibro(LibroModel libro) {
        return libroRepository.save(libro);
    }

    @Override
    public LibroModel obtenerLibroPorId(int id) {
        return libroRepository.findById(id)
                .orElseThrow(() ->
                        new RecursoNoEncontradoException("Libro con ID " + id + " no existe"));
    }

    @Override
    public void eliminarLibro(int id) {

        // Primero se valida que el libro exista
        libroRepository.findById(id)
                .orElseThrow(() ->
                        new RecursoNoEncontradoException("Libro con ID " + id + " no existe"));

        // Luego se elimina
        libroRepository.deleteById(id);
    }
}
