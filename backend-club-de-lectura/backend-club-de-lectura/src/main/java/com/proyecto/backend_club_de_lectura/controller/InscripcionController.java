package com.proyecto.backend_club_de_lectura.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.proyecto.backend_club_de_lectura.model.InscripcionModel;
import com.proyecto.backend_club_de_lectura.service.IInscripcionService;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;


@RestController
@RequestMapping("/api/inscripciones")
public class InscripcionController {
        @Autowired
    private IInscripcionService inscripcionService;

    // DTO interno para recibir JSON { "idUsuario": 1, "idReto": 5 }
    public static class InscripcionRequest {
        public int idUsuario;
        public int idReto;
    }

    // --------------------------
    // 1. Inscribir usuario a reto
    // --------------------------
    @PostMapping("/inscribir")
    public ResponseEntity<InscripcionModel> inscribirUsuario(@RequestBody InscripcionRequest request) {
        
        InscripcionModel nueva = inscripcionService.inscribirUsuarioEnReto(
                request.idUsuario,
                request.idReto
        );

        return new ResponseEntity<>(nueva, HttpStatus.CREATED);
    }

    // --------------------------
    // 2. Cancelar inscripción
    // --------------------------
    @PutMapping("/cancelar/{idInscripcion}")
    public ResponseEntity<InscripcionModel> cancelar(@PathVariable int idInscripcion) {
        InscripcionModel cancelada = inscripcionService.cancelarInscripcion(idInscripcion);
        return ResponseEntity.ok(cancelada);
    }

    // --------------------------
    // 3. Listar inscripciones de un usuario
    // --------------------------
    @GetMapping("/usuario/{idUsuario}")
    public ResponseEntity<?> listarPorUsuario(@PathVariable int idUsuario) {
        return ResponseEntity.ok(inscripcionService.obtenerInscripcionesPorUsuario(idUsuario));
    }

    // --------------------------
    // 4. Obtener inscripción por ID
    // --------------------------
    @GetMapping("/{idInscripcion}")
    public ResponseEntity<?> buscarPorId(@PathVariable int idInscripcion) {
        return ResponseEntity.ok(inscripcionService.buscarPorId(idInscripcion));
    }
}

