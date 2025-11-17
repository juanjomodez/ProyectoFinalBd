package com.proyecto.backend_club_de_lectura.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.proyecto.backend_club_de_lectura.exception.RecursoNoEncontradoException;
import com.proyecto.backend_club_de_lectura.model.LibroModel;
import com.proyecto.backend_club_de_lectura.service.ILibroService;

@RestController
@RequestMapping("/api/libros")
public class LibroController {

    @Autowired
    private ILibroService libroService;

    // Obtener todos los libros
    @GetMapping
    public List<LibroModel> listarLibros() {
        return libroService.listarLibros();
    }

    // Obtener libro por ID
    @GetMapping("/obtener/{id}")
    public LibroModel obtenerLibro(@PathVariable int id) {
        LibroModel libro = libroService.obtenerLibroPorId(id);

        if (libro == null) {
            throw new RecursoNoEncontradoException("Libro con id " + id + " no existe.");
        }

        return libro;
    }

    // Crear libro
    @PostMapping("/guardar")
    public LibroModel guardarLibro(@RequestBody LibroModel libro) {
        return libroService.guardarLibro(libro);
    }

    // Actualizar libro
    @PutMapping("actualizar/{id}")
    public LibroModel actualizarLibro(@PathVariable int id, @RequestBody LibroModel libro) {

        LibroModel existente = libroService.obtenerLibroPorId(id);

        if (existente == null) {
            throw new RecursoNoEncontradoException("No se puede actualizar. El libro con id " + id + " no existe.");
        }

        libro.setIdLibro(id);
        return libroService.guardarLibro(libro);
    }

    // Eliminar
    @DeleteMapping("eliminar/{id}")
    public String eliminarLibro(@PathVariable int id) {

        LibroModel existente = libroService.obtenerLibroPorId(id);

        if (existente == null) {
            throw new RecursoNoEncontradoException("No se puede eliminar. El libro con id " + id + " no existe.");
        }

        libroService.eliminarLibro(id);
        return "Libro eliminado correctamente.";
    }
}
