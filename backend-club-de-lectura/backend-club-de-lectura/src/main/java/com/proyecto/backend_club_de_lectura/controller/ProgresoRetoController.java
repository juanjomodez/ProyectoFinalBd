package com.proyecto.backend_club_de_lectura.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.proyecto.backend_club_de_lectura.model.ProgresoRetoModel;
import com.proyecto.backend_club_de_lectura.service.IProgresoRetoService;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;


@RestController // Indica que esta clase es un controlador REST
@RequestMapping("/api/progreso-reto") // Ruta base para las solicitudes relacionadas con el progreso del
public class ProgresoRetoController {
    @Autowired
    private IProgresoRetoService progresoRetoService;

    // DTO para registrar progreso
    public static class RegistrarProgresoRequest {
        public Integer idInscripcion;
        public Integer idLibro;
        public Double porcentaje;
    }

    // DTO para actualizar progreso
    public static class ActualizarProgresoRequest {
        public Double nuevoPorcentaje;
    }

    // -----------------------------------------------
    // 1. Registrar progreso en un libro del reto
    // -----------------------------------------------
    @PostMapping("/registrar")
    public ResponseEntity<ProgresoRetoModel> registrar(@RequestBody RegistrarProgresoRequest request) {

        ProgresoRetoModel nuevo = progresoRetoService.registrarProgreso(
            request.idInscripcion,
            request.idLibro,
            request.porcentaje
        );

        return new ResponseEntity<>(nuevo, HttpStatus.CREATED);
    }

    // -----------------------------------------------
    // 2. Actualizar porcentaje de progreso
    // -----------------------------------------------
    @PutMapping("/actualizar/{idProgreso}")
    public ResponseEntity<ProgresoRetoModel> actualizar(
            @PathVariable Integer idProgreso,
            @RequestBody ActualizarProgresoRequest request) {

        ProgresoRetoModel actualizado = progresoRetoService.actualizarProgreso(
            idProgreso,
            request.nuevoPorcentaje
        );

        return ResponseEntity.ok(actualizado);
    }

    // -----------------------------------------------
    // 3. Obtener progreso de una inscripción específica
    // -----------------------------------------------
    @GetMapping("/inscripcion/{idInscripcion}")
    public ResponseEntity<List<ProgresoRetoModel>> obtenerPorInscripcion(
            @PathVariable Integer idInscripcion) {

        List<ProgresoRetoModel> progreso = progresoRetoService.obtenerProgresoDeInscripcion(idInscripcion);
        return ResponseEntity.ok(progreso);
    }

    // -----------------------------------------------
    // 4. Obtener un progreso específico por ID
    // -----------------------------------------------
    @GetMapping("/{idProgreso}")
    public ResponseEntity<ProgresoRetoModel> obtenerPorId(
            @PathVariable Integer idProgreso) {

        ProgresoRetoModel progreso = progresoRetoService.obtenerProgresoPorId(idProgreso);
        return ResponseEntity.ok(progreso);
    }
}
