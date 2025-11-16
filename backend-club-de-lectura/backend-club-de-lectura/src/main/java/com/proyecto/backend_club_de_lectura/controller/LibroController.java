package com.proyecto.backend_club_de_lectura.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.proyecto.backend_club_de_lectura.model.LibroModel;
import com.proyecto.backend_club_de_lectura.service.ILibroService;

@RestController
@RequestMapping("/api/libros")
public class LibroController {

    @Autowired
    private ILibroService libroService;

    @GetMapping
    public List<LibroModel> listarLibros() {
        return libroService.listarLibros();
    }

    @GetMapping("/{id}")
    public LibroModel obtenerLibro(@PathVariable int id) {
        return libroService.obtenerLibroPorId(id);
    }

    @PostMapping
    public LibroModel guardarLibro(@RequestBody LibroModel libro) {
        return libroService.guardarLibro(libro);
    }

    @PutMapping("/{id}")
    public LibroModel actualizarLibro(@PathVariable int id, @RequestBody LibroModel libro) {
        libro.setIdLibro(id); 
        return libroService.guardarLibro(libro);
    }

    @DeleteMapping("/{id}")
    public void eliminarLibro(@PathVariable int id) {
        libroService.eliminarLibro(id);
    }
}
