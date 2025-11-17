package com.proyecto.backend_club_de_lectura.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.proyecto.backend_club_de_lectura.exception.ErrorLogicoException;
import com.proyecto.backend_club_de_lectura.exception.RecursoNoEncontradoException;
import com.proyecto.backend_club_de_lectura.model.LibroModel;
import com.proyecto.backend_club_de_lectura.model.LibrosRetoModel;
import com.proyecto.backend_club_de_lectura.model.RetoLecturaModel;
import com.proyecto.backend_club_de_lectura.repository.ILibroRepository;
import com.proyecto.backend_club_de_lectura.repository.ILibrosRetoRepository;
import com.proyecto.backend_club_de_lectura.repository.IRetoLecturaRepository;

@Service
public class LibrosRetoServiceImp implements ILibrosRetoService {

    @Autowired
    private ILibrosRetoRepository librosRetoRepository;

    @Autowired
    private ILibroRepository libroRepository;

    @Autowired
    private IRetoLecturaRepository retoRepository;

    @Override
    public LibrosRetoModel agregarLibroAReto(Integer idLibro, Integer idReto) {

        // ❌ Regla de negocio → ya existe la relación
        if (librosRetoRepository.existsByLibro_IdLibroAndReto_IdReto(idLibro, idReto)) {
            throw new ErrorLogicoException("El libro ya está asociado a este reto.");
        }

        // ✔ Libro existente
        LibroModel libro = libroRepository.findById(idLibro)
                .orElseThrow(() -> new RecursoNoEncontradoException("Libro con ID " + idLibro + " no encontrado."));

        // ✔ Reto existente
        RetoLecturaModel reto = retoRepository.findById(idReto)
                .orElseThrow(() -> new RecursoNoEncontradoException("Reto con ID " + idReto + " no encontrado."));

        // ✔ Crear relación
        LibrosRetoModel relacion = new LibrosRetoModel();
        relacion.setLibro(libro);
        relacion.setReto(reto);

        return librosRetoRepository.save(relacion);
    }

    @Override
    public List<LibrosRetoModel> obtenerLibrosPorReto(Integer idReto) {

        // ⚠ Si deseas, puedes validar que el reto exista:
        retoRepository.findById(idReto)
                .orElseThrow(() -> new RecursoNoEncontradoException("Reto con ID " + idReto + " no encontrado."));

        List<LibrosRetoModel> lista = librosRetoRepository.findAll();

        // FILTRADO BÁSICO
        List<LibrosRetoModel> resultado = new java.util.ArrayList<>();

        for (LibrosRetoModel lr : lista) {
            if (lr.getReto().getIdReto() == idReto) {
                resultado.add(lr);
            }
        }

        return resultado;
    }

    @Override
    public void eliminarLibroDeReto(Integer idLibrosReto) {

        // ✔ Validar existencia antes de eliminar
        librosRetoRepository.findById(idLibrosReto)
            .orElseThrow(() -> new RecursoNoEncontradoException(
                "La relación libro-reto con ID " + idLibrosReto + " no existe."
            ));

        librosRetoRepository.deleteById(idLibrosReto);
    }
}
