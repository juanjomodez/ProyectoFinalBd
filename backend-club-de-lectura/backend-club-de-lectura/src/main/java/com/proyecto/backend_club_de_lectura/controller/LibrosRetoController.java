package com.proyecto.backend_club_de_lectura.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.proyecto.backend_club_de_lectura.model.LibrosRetoModel;
import com.proyecto.backend_club_de_lectura.service.ILibrosRetoService;

@RestController
@RequestMapping("/api/libros-reto")
public class LibrosRetoController {

    @Autowired
    private ILibrosRetoService librosRetoService;

    // agrega un libro a un reto
    @PostMapping("/agregar/{idLibro}/{idReto}")
    public ResponseEntity<LibrosRetoModel> agregar(
            @PathVariable Integer idLibro,
            @PathVariable Integer idReto) {

        LibrosRetoModel nuevaRelacion = librosRetoService.agregarLibroAReto(idLibro, idReto);
        return ResponseEntity.ok(nuevaRelacion);
    }

    // obtener todos los libros de un reto
    @GetMapping("/reto/{idReto}")
    public ResponseEntity<List<LibrosRetoModel>> obtenerPorReto(@PathVariable Integer idReto) {
        List<LibrosRetoModel> lista = librosRetoService.obtenerLibrosPorReto(idReto);
        return ResponseEntity.ok(lista);
    }

    // elimina un libro del reto
    @DeleteMapping("/eliminar/{idLibrosReto}")
    public ResponseEntity<String> eliminar(@PathVariable Integer idLibrosReto) {
        librosRetoService.eliminarLibroDeReto(idLibrosReto);
        return ResponseEntity.ok("Libro eliminado del reto correctamente.");
    }
}
