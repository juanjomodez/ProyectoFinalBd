package com.proyecto.backend_club_de_lectura.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.proyecto.backend_club_de_lectura.model.LibroModel;
import com.proyecto.backend_club_de_lectura.repository.ILibroRepository;

@Service  // indica que es un servicio
public class LibroServiceImp implements ILibroService {

    @Autowired
    private ILibroRepository libroRepository; // conecta con BD

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
        return libroRepository.findById(id).orElse(null);
    }

    @Override
    public void eliminarLibro(int id) {
        libroRepository.deleteById(id);
    }
}
