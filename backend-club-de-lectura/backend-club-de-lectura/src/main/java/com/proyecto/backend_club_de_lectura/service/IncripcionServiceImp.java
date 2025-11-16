package com.proyecto.backend_club_de_lectura.service;

import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.proyecto.backend_club_de_lectura.model.InscripcionModel;
import com.proyecto.backend_club_de_lectura.model.RetoLecturaModel;
import com.proyecto.backend_club_de_lectura.model.UsuarioModel;
import com.proyecto.backend_club_de_lectura.repository.IInscripcionRepository;
import com.proyecto.backend_club_de_lectura.repository.IRetoLecturaRepository;
import com.proyecto.backend_club_de_lectura.repository.IUsuarioRepository;

@Service
public class IncripcionServiceImp implements IInscripcionService {

    @Autowired IInscripcionRepository inscripcionRepository;

    @Autowired IUsuarioRepository usuarioRepository;

    @Autowired IRetoLecturaRepository retoLecturaRepository;

    // ------------------------------
    // 1. Inscribir usuario en un reto
    // ------------------------------
    @Override
    public InscripcionModel inscribirUsuarioEnReto(int idUsuario, int idReto) {
        
                // Verificar si ya existe inscripción previa
        Optional<InscripcionModel> existente =
                inscripcionRepository.findByUsuarioIdUsuarioAndRetoIdReto(idUsuario, idReto);

        if (existente.isPresent()) {
            throw new RuntimeException("El usuario ya está inscrito en este reto.");
        }

        // Obtener usuario
        UsuarioModel usuario = usuarioRepository.findById(idUsuario)
                .orElseThrow(() -> new RuntimeException("Usuario no encontrado"));

        // Validar rol
        if (usuario.getRol() != UsuarioModel.Rol.lector) {
            throw new RuntimeException("Solo los usuarios con rol 'lector' pueden inscribirse.");
        }

        // Obtener reto
        RetoLecturaModel reto = retoLecturaRepository.findById(idReto)
                .orElseThrow(() -> new RuntimeException("Reto no encontrado"));

        // Crear inscripción (revisar la estructura si es la misma con inscripcion model)
        InscripcionModel nueva = new InscripcionModel();
        nueva.setUsuario(usuario);
        nueva.setReto(reto);
        nueva.setFecha(new Date());
        nueva.setEstadoInscripcion(InscripcionModel.EstadoInscripcion.activa);

        return inscripcionRepository.save(nueva);
    }

    // Cancelar inscripción
    @Override
    public InscripcionModel cancelarInscripcion(int idInscripcion) {

        InscripcionModel inscripcion = inscripcionRepository.findById(idInscripcion)
                .orElseThrow(() -> new RuntimeException("Inscripción no encontrada"));

        inscripcion.setEstadoInscripcion(InscripcionModel.EstadoInscripcion.cancelada);

        return inscripcionRepository.save(inscripcion);
    }

    // Listar inscripciones por usuario
    @Override
    public List<InscripcionModel> obtenerInscripcionesPorUsuario(int idUsuario) {
        return inscripcionRepository.findByUsuarioIdUsuario(idUsuario);
    }

    // Buscar una inscripción por ID
    @Override
    public InscripcionModel buscarPorId(int idInscripcion) {
        return inscripcionRepository.findById(idInscripcion)
                .orElseThrow(() -> new RuntimeException("Inscripción no encontrada"));
    }
}

