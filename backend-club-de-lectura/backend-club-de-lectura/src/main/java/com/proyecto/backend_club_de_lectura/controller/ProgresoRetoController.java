package com.proyecto.backend_club_de_lectura.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.proyecto.backend_club_de_lectura.model.ProgresoRetoModel;
import com.proyecto.backend_club_de_lectura.service.IProgresoRetoService;


@RestController // indica que esta clase es un controlador REST
@RequestMapping("/api/progreso-reto") // ruta base para las solicitudes relacionadas con el progreso del
public class ProgresoRetoController {
    @Autowired
    private IProgresoRetoService progresoRetoService;

    // al solo querer 3 datos que de el usuario usamos un DTO(data transfer object) que nos ayuda a enviar datos
    // nos sirve para que se envie solo la informacion necesaria para registrar progreso
    public static class RegistrarProgresoRequest {
        public Integer idInscripcion;
        public Integer idLibro;
        public Double porcentaje;
    }

    // tenemos este DTO para actualizar progreso
    public static class ActualizarProgresoRequest {
        public Double nuevoPorcentaje;
    }

    // registrar progreso en un libro del reto
    @PostMapping("/registrar") //con el post creamos
    public ResponseEntity<ProgresoRetoModel> registrar(@RequestBody RegistrarProgresoRequest request) { //con esto ultimo se convierte el json que da el usuario en java
        // el controller recibe el json que da el usuario y lo guarda dentro de RegistrarProgresoRequest
        // que es un dto

        ProgresoRetoModel nuevo = progresoRetoService.registrarProgreso( //llamamos al service para que se encargue de validar que todo este correcto
            request.idInscripcion,
            request.idLibro,
            request.porcentaje
        );

        return new ResponseEntity<>(nuevo, HttpStatus.CREATED); // responde que el objeto ha sido creado
    }

    
    // actualizar porcentaje de progreso
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

    // obtener progreso de una inscripción específica
    @GetMapping("/inscripcion/{idInscripcion}")
    public ResponseEntity<List<ProgresoRetoModel>> obtenerPorInscripcion(
            @PathVariable Integer idInscripcion) {

        List<ProgresoRetoModel> progreso = progresoRetoService.obtenerProgresoDeInscripcion(idInscripcion);
        return ResponseEntity.ok(progreso);
    }

    
    // obtener un progreso específico por ID
    @GetMapping("/{idProgreso}")
    public ResponseEntity<ProgresoRetoModel> obtenerPorId(
            @PathVariable Integer idProgreso) {

        ProgresoRetoModel progreso = progresoRetoService.obtenerProgresoPorId(idProgreso);
        return ResponseEntity.ok(progreso);
    }
}
