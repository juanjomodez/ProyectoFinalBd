package com.proyecto.backend_club_de_lectura.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.proyecto.backend_club_de_lectura.model.RetoLecturaModel;
import com.proyecto.backend_club_de_lectura.service.IRetoLecturaService;

@RestController
@RequestMapping("/api/retos")
public class RetoLecturaController {

    @Autowired
    private IRetoLecturaService retoService;

    // listar todos los retos
    @GetMapping // get sirve para obtener datos
    public List<RetoLecturaModel> listarRetos() {
        return retoService.listarRetos();
    }

    // obtener un reto por id
    @GetMapping("/obtenerReto/{id}")
    public RetoLecturaModel obtenerReto(@PathVariable int id) {
        return retoService.obtenerRetoPorId(id);
    }

    // crear un nuevo reto
    @PostMapping("/crear") //post sirve para crear nuevos datos
    public RetoLecturaModel crearReto(@RequestBody RetoLecturaModel reto) {
        return retoService.guardarReto(reto);
    }

    // actualizar un reto existente
    @PutMapping("/actualizar/{id}") //put sirve para actualizar informacion ya existente
    public RetoLecturaModel actualizarReto(@PathVariable int id, @RequestBody RetoLecturaModel reto) {
        return retoService.actualizarReto(id, reto);
    }


    // eliminar un reto (solo si no tiene inscritos)
    @DeleteMapping("/eliminar/{id}") //delete sirve para eliminar informacion existente
    public ResponseEntity<String> eliminarReto(@PathVariable int id) {
    try {
        retoService.eliminarReto(id);
        return ResponseEntity.ok("Reto eliminado correctamente");
    } catch (Exception e) {
        return ResponseEntity.badRequest().body(e.getMessage());
    }
}
}
